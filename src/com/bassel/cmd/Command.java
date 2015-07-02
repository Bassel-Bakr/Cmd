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
import java.io.PrintStream;

public abstract class Command
{
	private String[] args;
	private int currentArg;

	public void run(String[] args)
	{
		this.args = args;
		currentArg = -1;
		try
		{onRun();}
		catch (Exception e)
		{onExceptionCaught(e);}
	}

	public void showUsage()
	{
		try
		{onShowUsage();}
		catch (Exception e)
		{onExceptionCaught(e);}
	}

	public int argsCount()
	{
		return args.length;
	}

	public String getArg(int index)
	{
		if (argsCount() > index++) return args[index--];
		return null;
	}

	public String nextArg()
	{
		if (argsCount() > currentArg++)
		{return args[currentArg];}
		return null;
	}

	public final abstract void main(String[] args);
	public abstract void onRun();
	public abstract void onShowUsage();
	public abstract void onExceptionCaught(Exception exception);
}
