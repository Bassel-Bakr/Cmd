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

public class Output
{
	private String output;
	private int exitValue;

	private Output(int exitValue, String output)
	{
		this.exitValue = exitValue;
		this.output = output;
	}

	public static Output newInstance(int exitValue, String output)
	{
		return new Output(exitValue, output);
	}

	public boolean success()
	{
		return exitValue == 0;
	}

	public String getString()
	{return output;}

	public String[] getArray()
	{return (String[]) Convert.string2array(output);}

	public List<String> getList()
	{return Convert.string2list(output);}

	public int getExitValue()
	{return exitValue;}

	public String toString()
	{
		return String.format("Status: %s\nOutput: %s", success() ? "success!" : "error!", output);
	}
}
