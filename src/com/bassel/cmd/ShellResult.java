/* 
 * Copyright (C) 2013-2014 Bassel Bakr
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
import java.io.PrintWriter;
import java.util.List;

public class ShellResult
{
	private static final int EXIT_VALUE_UNKNOWN = -1;
	private String result;
	private int exitValue;
	private PrintWriter pw;

	private ShellResult(String result, int exitValue)
	{
		this.result = result;
		this.exitValue = exitValue;
	}

	public void setWriter(PrintWriter pw) {
			this.pw = pw;
	}

	public PrintWriter getWriter() {
			return pw;
	}

	public static ShellResult newInstance(String result)
	{return new ShellResult(result, EXIT_VALUE_UNKNOWN);}

	public static ShellResult newInstance(String result, int exitValue)
	{return new ShellResult(result, exitValue);}

	public String getString() 
	{try
		{
			return result;
		}
		catch (Exception e)
		{e.printStackTrace();
			return null;}}

	public String[] getArray()
	{try
		{
			return Convert.string2array(result);
		}
		catch (Exception e)
		{e.printStackTrace();
			return null;}}

	public List<String> getList()
	{try
		{
			return Convert.string2list(result);
		}
		catch (Exception e)
		{e.printStackTrace();
			return null;}}

	public int getExitValue()
	{return exitValue;}
}
