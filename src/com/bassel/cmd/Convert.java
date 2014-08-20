/* 
 * Copyright (C) 2013-2014 Bassel
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

import java.util.Arrays;
import java.util.List;

public class Convert
{
	public static List array2list(Object[] array)
	{
		return Arrays.asList(array);
	}

	public static String array2string(Object[] array)
	{
		StringBuilder string = new StringBuilder();
		for (Object line : array)
		{
			string.append(line + "\n");
		}
		String g = null;
		try
		{
			g = string.substring(0, string.length() - 1).toString();
		}
		catch(Exception e)
		{
			return "";
		}
		return g;
	}

	public static Object[] list2array(List list)
	{
		return list.toArray();
	}

	public static String list2string(List list)
	{
		StringBuilder string = new StringBuilder();
		for (String line : list)
		{
			string.append(line + "\n");
		}
		return string.deleteCharAt(string.length()).toString();
	}

	public static Object[] string2array(String string)
	{
		return string.split("\n");
	}

	public static List string2list(String string)
	{
		return array2list(string.split("\n"));
	}
}
