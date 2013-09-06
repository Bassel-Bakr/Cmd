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

import android.os.*;
import android.util.*;
import java.io.*;
import java.util.*;

import java.lang.Process;

public class Cmd extends AsyncTask<List,Void,String>
{
	public static boolean Debug;
	private static boolean stderr;
	private static Process p;
	private static ProcessBuilder pb;
	private static String ns, cmd, line, output;
	private static List[] sl;
	private static StringBuilder sb;
	private static BufferedReader stdout;

	private static String doInBackground(List[] list)
	{
		ns = list[0].get(0).toString();
		stderr = Boolean.valueOf(list[0].get(1).toString());
		list[0] = list[0].subList(2, list[0].size());
		cmd = Cmd.Convert.list2string(list[0]);
		try
		{
			pb = new ProcessBuilder(ns,"-c",cmd).redirectErrorStream(stderr);
			p = pb.start();
			stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
			p.waitFor();
			sb = new StringBuilder();
			while ((line = stdout.readLine()) != null)
			{
				sb.append(line).append((char)10);
			}
			stdout.close();
			p.destroy();
			output = sb.substring(0, sb.length() - 1);
			if (Debug)
			{
				Log.d("[STDIN]", ns + " -c " + cmd);
				Log.d("[STDOUT]", output);
			}
			return output;
		}
		catch (Exception e)
		{
			if (Debug)
			{
				Log.d("ShellException", e.toString());
			}
		}
		return null;
	}

	private static String getOutput(String ns, String cmd, boolean stderr)
	{
		List ls = new ArrayList();
		ls.add(ns);
		ls.add(stderr);
		ls.add(cmd);
		sl = new List[1];
		sl[0] = ls;
		return doInBackground(sl);
	}

	public static boolean root()
	{
		try
		{
			if (Cmd.Convert.string2int(Cmd.SU.ex("echo $USER_ID")) == 0)
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

	public static class Convert
	{
		public static String bool2string(boolean b)
		{
			return String.valueOf(b);
		}

		public static int string2int(String s)
		{
			return Integer.parseInt(s);
		}

		public static String[] string2array(String s)
		{
			return s.split("\n");
		}

		public static List string2list(String s)
		{
			return Arrays.asList(s.split("\n"));
		}

		public static String list2string(List<String> ls)
		{
			sb = new StringBuilder();
			for (String line : ls)
			{
				sb.append(line).append((char)10);
			}
			return sb.substring(0, sb.length() - 1);
		}

		public static String array2string(String[] sa)
		{
			sb = new StringBuilder();
			for (int i = 0; i < sa.length; i++)
			{
				sb.append(sa[i]).append((char)10);
			}
			return sb.substring(0, sb.length() - 1);
		}

		public static String[] list2array(List<String> ls)
		{
			return ls.toArray(new String[ls.size()]);
		}

		public static List array2list(String[] sa)
		{
			return Arrays.asList(sa);
		}
	}
}
