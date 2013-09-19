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

public class Cmd
{
	public static boolean Debug;
	private static List<String> output;
	private static Execute process;

	private static String getOutput(String ns, List<String> cmd, boolean stderr)
	{
		output = new ArrayList<String>();
		process = new Execute(ns, cmd, stderr, output);
		process.start();
		try
		{
			process.join();
			return Convert.list2string(output);
		}
		catch (StringIndexOutOfBoundsException e)
		{
			return "";
		}
		catch(InterruptedException e)
		{
			Log.e("[CMD PROCESS INTERRUPTED]", e.toString());
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
			return Cmd.getOutput("su" , Convert.string2list(cmd), false);
		}

		public static String ex(String[] cmd)
		{
			return Cmd.getOutput("su", Convert.array2list(cmd), false);
		}

		public static String ex(List<String> cmd)
		{
			return Cmd.getOutput("su" , cmd, false);
		}

		public static String ex(String cmd, boolean stderr)
		{
			return Cmd.getOutput("su" , Convert.string2list(cmd), stderr);
		}

		public static String ex(String[] cmd, boolean stderr)
		{
			return Cmd.getOutput("su", Convert.array2list(cmd), stderr);
		}

		public static String ex(List<String> cmd, boolean stderr)
		{
			return Cmd.getOutput("su" , cmd, stderr);
		}
	}

	public static class SH
	{
		public static String ex(String cmd)
		{
			return Cmd.getOutput("sh" , Convert.string2list(cmd), false);
		}

		public static String ex(String[] cmd)
		{
			return Cmd.getOutput("sh", Convert.array2list(cmd), false);
		}

		public static String ex(List<String> cmd)
		{
			return Cmd.getOutput("sh" , cmd, false);
		}

		public static String ex(String cmd, boolean stderr)
		{
			return Cmd.getOutput("sh" , Convert.string2list(cmd), stderr);
		}

		public static String ex(String[] cmd, boolean stderr)
		{
			return Cmd.getOutput("sh", Convert.array2list(cmd), stderr);
		}

		public static String ex(List<String> cmd, boolean stderr)
		{
			return Cmd.getOutput("sh" , cmd, stderr);
		}
	}
}
