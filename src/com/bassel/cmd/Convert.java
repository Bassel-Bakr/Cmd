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

import java.util.*;

public class Convert
{

	private static StringBuilder sb;

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
