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

import java.io.*;
import java.util.*;
import android.util.*;

public class Stdin extends Thread
{
	private BufferedWriter stdin;
	private String shell;
	private List<String> cmd;

	public Stdin(String shell ,OutputStream osw, List<String> cmd)
	{
		stdin = new BufferedWriter(new OutputStreamWriter(osw));
		this.shell= shell;
		this.cmd = cmd;
	}

	public void run()
	{
		try
		{
			for(int i = 0; i < cmd.size(); i++)
			{
				stdin.write(cmd.get(i)+"\n");
				stdin.flush();
			}
			stdin.write("exit\n");
			stdin.flush();
			stdin.close();
			if(Cmd.Debug)
			{
				Log.d("[CMD STDIN]["+shell.toUpperCase()+"]", Convert.list2string(cmd));
			}
		}
		catch (Exception e)
		{}
	}
}
