/* 
 * Copyright (C) 2013 Bassel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bassel.cmd;

import android.util.*;
import java.util.*;
import java.util.concurrent.*;

public class Execute extends Thread
{
	private boolean errors;
	private String ns;
	private List<String> cmd, output;
	private Process process;
	private Stdin stdin;
	private Stdout stdout;
	private Stderr stderr;
	private Destroy destroy;

	public Execute(String ns, List<String> cmd, boolean errors, List<String> output)
	{
		this.ns = ns;
		this.cmd = cmd;
		this.errors = errors;
		this.output = output;
	}

	public void run()
	{
		try
		{
			process = new ProcessBuilder(ns).redirectErrorStream(errors).start();
			stdin = new Stdin(ns,process.getOutputStream(), cmd);
			stdout = new Stdout(process.getInputStream(), output);
			if(Cmd.Debug && !errors) stderr = new Stderr(process.getErrorStream());
			destroy = new Destroy(process);
			stdout.start();
			if(Cmd.Debug && !errors) stderr.start();
			stdin.start();
			process.waitFor();
			stdout.join();
			if(Cmd.Debug && !errors) stderr.join();
			destroy.start();
		}
		catch (Exception e)
		{
			Log.d("[CMD ERROR]", e.toString());
		}
	}
}
