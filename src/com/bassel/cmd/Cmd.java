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

public class Cmd
{
	public static boolean Debug;
	private static Process process;
	private static Output stdout;
	private static List<String> output;
	private static Thread destroy;

	private static String getOutput(String ns, String cmd, boolean stderr)
	{
		try
		{
			output = new ArrayList<String>();
			process = new ProcessBuilder(ns, "-c", cmd).redirectErrorStream(stderr).start();
			stdout = new Output(process.getInputStream(), output);
			stdout.start();
			destroy = new Thread(new Runnable()
				{
					public void run()
					{
						process.destroy();
					}				
			});
			stdout.join();
			destroy.start();
			return Convert.list2string(output);
		}

		catch (Exception e)
		{
			Log.d("SHELL", e.toString());
			return null;
		}
	}

	public static boolean root()
	{
		try
		{
			if (Convert.string2int(Cmd.SU.ex("echo $USER_ID")) == 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (NumberFormatException nfe)
		{
			return false;
		}
	}

	public static class SU
	{
		public static String ex(String cmd)
		{
			return Cmd.getOutput("su" , cmd, false);
		}

		public static String ex(String[] cmd)
		{
			return Cmd.getOutput("su", Convert.array2string(cmd), false);
		}

		public static String ex(List<String> cmd)
		{
			return Cmd.getOutput("su" , Convert.list2string(cmd), false);
		}

		public static String ex(String cmd, boolean stderr)
		{
			return Cmd.getOutput("su" , cmd, stderr);
		}

		public static String ex(String[] cmd, boolean stderr)
		{
			return Cmd.getOutput("su", Convert.array2string(cmd), stderr);
		}

		public static String ex(List<String> cmd, boolean stderr)
		{
			return Cmd.getOutput("su" , Convert.list2string(cmd), stderr);
		}
	}

	public static class SH
	{
		public static String ex(String cmd)
		{
			return Cmd.getOutput("sh" , cmd, false);
		}

		public static String ex(String[] cmd)
		{
			return Cmd.getOutput("sh", Convert.array2string(cmd), false);
		}

		public static String ex(List<String> cmd)
		{
			return Cmd.getOutput("sh" , Convert.list2string(cmd), false);
		}

		public static String ex(String cmd, boolean stderr)
		{
			return Cmd.getOutput("sh" , cmd, stderr);
		}

		public static String ex(String[] cmd, boolean stderr)
		{
			return Cmd.getOutput("sh", Convert.array2string(cmd), stderr);
		}

		public static String ex(List<String> cmd, boolean stderr)
		{
			return Cmd.getOutput("sh" , Convert.list2string(cmd), stderr);
		}
	}
}
