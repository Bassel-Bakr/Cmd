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
import java.io.*;
import java.util.*;


public class Stdout extends Thread
{
	private String line;
	private List<String> output;
	private BufferedReader stdout;

	public Stdout(InputStream inputStream, List<String> output)
	{
		stdout = new BufferedReader(new InputStreamReader(inputStream));
		this.output = output;
	}

	public void run()
	{
		try
		{
			while ((line = stdout.readLine()) != null)
			{
				output.add(line);
			}
			stdout.close();
			
			if (Cmd.Debug)
			{
				Log.d("[CMD STDOUT]", Convert.list2string(output));
			}
		}
		catch (Exception e)
		{
			//Log.e("[CMD STDOUT ERROR]", e.toString());
		}
	}
}
