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
import android.util.Log;
import java.util.List;

public class Debug
{
	private static boolean DEBUG;
	
	public static void debug(boolean DEBUG)
	{
		Debug.DEBUG = DEBUG;
	}

	public static void log(String command)
	{
		if (!DEBUG) return;
		Log.d("CmdShell", command);
	}

	public static void log(String[] commands)
	{
		if (!DEBUG) return;
		for (String command : commands)
			Log.d("CmdShell", command);
	}

	public static void log(List<String> commands)
	{
		if (!DEBUG) return;
		for (String command : commands)
			Log.d("CmdShell", command);
	}
}
