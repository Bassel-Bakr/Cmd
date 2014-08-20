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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Cmd
{
	private static Process proc;
	private boolean interactive, excludeErrorStream;
	private String initialCommand;
	private volatile StringBuilder result;
	private Listeners.LineListener lineListener;
	private PrintWriter stdin;
	private Runnable stdout/*, stderr*/;
	private static Pool pool;

	private Cmd(Shell builder)
	{
		proc = builder.proc;
		initialCommand = builder.initialCommand;
		interactive = builder.interactive;
		lineListener = builder.lineListener;
		result = builder.result;
		stdout = builder.stdout;
		//stderr = builder.stderr;
		stdin = builder.stdin;
		pool = Pool.getInstance();
		pool.execute(stdout);
		//pool.execute(stderr);
		pool.shutdown();
	}

	public static class SH
	{
		private static Cmd cmd;

		public static Output ex(String command, Object... args)
		{
			cmd = new Cmd.Shell()
				.rootAccess(false)
				.start();
			if (args != null)cmd.write(String.format(command, args));
			else cmd.write(command);
			try
			{
				cmd.proc.waitFor();
				//cmd.proc.destroy();
				pool.execute(Destroyer.newInstance());
			}
			catch (InterruptedException e)
			{e.printStackTrace();}
			try
			{
				return Output.newInstance(cmd.proc.exitValue(), cmd.result.substring(0, cmd.result.length() - 1));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return Output.newInstance(1, null);
			}
		}

		public static Output ex(String[] commands)
		{
			cmd = new Cmd.Shell()
				.rootAccess(false)
				.start();
			cmd.write(commands);
			try
			{
				cmd.proc.waitFor();
				//cmd.proc.destroy();
				pool.execute(Destroyer.newInstance());
			}
			catch (InterruptedException e)
			{e.printStackTrace();}
			try
			{
				return Output.newInstance(cmd.proc.exitValue(), cmd.result.substring(0, cmd.result.length() - 1));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return Output.newInstance(1, null);
			}
		}

		public static Output ex(List<String> commands)
		{
			cmd = new Cmd.Shell()
				.rootAccess(false)
				.start();
			cmd.write(commands);
			try
			{
				cmd.proc.waitFor();
				//cmd.proc.destroy();
				pool.execute(Destroyer.newInstance());
			}
			catch (InterruptedException e)
			{e.printStackTrace();}
			try
			{
				return Output.newInstance(cmd.proc.exitValue(), cmd.result.substring(0, cmd.result.length() - 1));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return Output.newInstance(1, null);
			}
		}

	}

	public static class SU
	{
		private static Cmd cmd;

		public static Output ex(String command, Object... args)
		{
			cmd = new Cmd.Shell()
				.rootAccess(true)
				.start();
			if (args != null)cmd.write(String.format(command, args));
			else cmd.write(command);
			try
			{
				cmd.proc.waitFor();
				//cmd.proc.destroy();
				pool.execute(Destroyer.newInstance());
			}
			catch (InterruptedException e)
			{e.printStackTrace();}
			try
			{
				return Output.newInstance(cmd.proc.exitValue(), cmd.result.substring(0, cmd.result.length() - 1));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return Output.newInstance(1, null);
			}
		}

		public static Output ex(String[] commands)
		{
			cmd = new Cmd.Shell()
				.rootAccess(true)
				.start();
			cmd.write(commands);
			try
			{
				cmd.proc.waitFor();
				//cmd.proc.destroy();
				pool.execute(Destroyer.newInstance());
			}
			catch (InterruptedException e)
			{e.printStackTrace();}
			try
			{
				return Output.newInstance(cmd.proc.exitValue(), cmd.result.substring(0, cmd.result.length() - 1));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return Output.newInstance(1, null);
			}
		}

		public static Output ex(List<String> commands)
		{
			cmd = new Cmd.Shell()
				.rootAccess(true)
				.start();
			cmd.write(commands);
			try
			{
				cmd.proc.waitFor();
				//cmd.proc.destroy();
				pool.execute(Destroyer.newInstance());
			}
			catch (InterruptedException e)
			{e.printStackTrace();}
			try
			{
				return Output.newInstance(cmd.proc.exitValue(), cmd.result.substring(0, cmd.result.length() - 1));
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return Output.newInstance(1, null);
			}
		}

	}

	private static class Shell
	{
		private Process proc;
		private boolean interactive = false,
		includeErrorStream = true;
		private String initialCommand;
		private volatile StringBuilder result;
		private Listeners.LineListener lineListener;
		private PrintWriter stdin;
		private Runnable stdout;

		public Shell excludeErrorStream()
		{
			this.includeErrorStream = false;
			return this;
		}
		
		public Shell setLineListener(Listeners.LineListener lineListener)
		{
			this.lineListener = lineListener;
			return this;
		}

		public Shell rootAccess(boolean root)
		{
			if (root)initialCommand = "su";
			else initialCommand = "sh";
			return this;
		}
		
		public Shell setInteractive(boolean interactive)
		{
			this.interactive = interactive;
			return this;
		}

		public Cmd start()
		{
			try
			{
				proc = new ProcessBuilder(initialCommand).redirectErrorStream(includeErrorStream).start();
			}
			catch (IOException e)
			{e.printStackTrace();}
			result = new StringBuilder();
			stdin = new PrintWriter(proc.getOutputStream());
			stdout = Stream.newInstance(proc.getInputStream(), lineListener, result);
			return new Cmd(this);
		}
	}
	
	private static class Destroyer implements Runnable
	{
		public static Destroyer newInstance()
		{
			return new Destroyer();
		}
		
		@Override
		public void run()
		{
			proc.destroy();
		}	
	}
	
	public static boolean root()
	{
		return RootUtils.root();
	}

	public void write(String command)
	{
		stdin.write((command + "\n"));
		stdin.flush();
		Debug.log(command);
		if (!interactive)
		{
			stdin.write("exit\n");
			stdin.flush();
			Debug.log("exit");
		}
	}

	public void write(String[] commands)
	{
		for (String command : commands)
		{
			stdin.write((command + "\n"));
			stdin.flush();
		}
		Debug.log(commands);
		if (!interactive)
		{
			stdin.write("exit\n");
			stdin.flush();
			Debug.log("exit");
		}
	}

	public void write(List<String> commands)
	{
		for (String command : commands)
		{
			stdin.write((command + "\n"));
			stdin.flush();
		}
		Debug.log(commands);
		if (!interactive)
		{
			stdin.write("exit\n");
			stdin.flush();
			Debug.log("exit");
		}
	}
}
