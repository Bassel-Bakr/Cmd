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
import java.util.List;
import java.nio.charset.Charset;

public class RootUtils
{

	public static boolean root()
	{
		return Cmd.SU.ex("id").success();
	}

	public static String getPartition(String partition, String... file)
	{
		try
		{
			List list = Cmd.SU.ex("cat '%s'", "/cache/recovery/last_log").getList();
			for (String line : list)
			{
				if (line.contains(partition) && line.contains("dev/block/"))
					for (String word : line.split(" "))
						if (word.contains("dev/block/")) return word;
			}
		}
		catch (Exception e)
		{e.printStackTrace();}
		return null;
	}

	public static boolean mount(String rw, String partition)
	{
		if (Cmd.SU.ex("mount -o %s,remount %s", rw, partition).success())return true;
		return false;
	}

	public static boolean copy(String location, String destination)
	{
		return Cmd.SU.ex("cp -fr '%s' '%s'", location, destination).success();
	}

}
