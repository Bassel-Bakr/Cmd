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

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Future;

public class Cmd {
		public static class SH {
				public static <T,U> Future<ShellResult> ex(T cmd, U... args) {
						try {
								File path = null;
								boolean redirectErrorStream = true;
								ILineListener lineListener = null;
								IResultListener resultListener = null;
								if (cmd instanceof String)
										cmd = (T) String.format((String) cmd, args);
								if (args != null)
										for (int i = 0; i < args.length; i++)
												if (args[i] instanceof ILineListener)
														lineListener = (ILineListener) args[i];
												else if (args[i] instanceof IResultListener)
														resultListener = (IResultListener) args[i];
												else if (args[i] instanceof Boolean)
														redirectErrorStream = args[i];
												else if (args[i] instanceof File)
														path = (File) args[i];
								return new Shell().start("sh", cmd, path, redirectErrorStream, lineListener, resultListener);
						} catch (IOException e) {Debug.log(e);
								e.printStackTrace();
								return null;}
				}

				public static <T> PrintWriter run(T... args) {
						try {
								File path = null;
								boolean redirectErrorStream = true;
								ILineListener lineListener = null;
								IResultListener resultListener = null;
								if (args != null)
										for (int i = 0; i < args.length; i++)
												if (args[i] instanceof ILineListener)
														lineListener = (ILineListener) args[i];
												else if (args[i] instanceof IResultListener)
														resultListener = (IResultListener) args[i];
												else if (args[i] instanceof Boolean)
														redirectErrorStream = args[i];
												else if (args[i] instanceof File)
														path = (File) args[i];
								return new Shell().startInteractive("sh", path, redirectErrorStream, lineListener, resultListener);
						} catch (IOException e) {Debug.log(e);
								return null;}
				}
		}

		public static class SU extends Shell {
				public static <T,U> Future<ShellResult> ex(T cmd, U... args) {
						try {
								File path = null;
								boolean redirectErrorStream = true;
								ILineListener lineListener = null;
								IResultListener resultListener = null;
								if (cmd instanceof String)
										cmd = (T) String.format((String) cmd, args);
								if (args != null)
										for (int i = 0; i < args.length; i++)
												if (args[i] instanceof ILineListener)
														lineListener = (ILineListener) args[i];
												else if (args[i] instanceof IResultListener)
														resultListener = (IResultListener) args[i];
												else if (args[i] instanceof Boolean)
														redirectErrorStream = args[i];
												else if (args[i] instanceof File)
														path = (File) args[i];
								return new Shell().start("su", cmd, path, redirectErrorStream, lineListener, resultListener);
						} catch (IOException e) {Debug.log(e);
								return null;}
				}

				public static <T> PrintWriter run(T... args) {
						try {
								File path = null;
								boolean redirectErrorStream = true;
								ILineListener lineListener = null;
								IResultListener resultListener = null;
								if (args != null)
										for (int i = 0; i < args.length; i++)
												if (args[i] instanceof ILineListener)
														lineListener = (ILineListener) args[i];
												else if (args[i] instanceof IResultListener)
														resultListener = (IResultListener) args[i];
												else if (args[i] instanceof Boolean)
														redirectErrorStream = args[i];
												else if (args[i] instanceof File)
														path = (File) args[i];
								return new Shell().startInteractive("su", path, redirectErrorStream, lineListener, resultListener);
						} catch (IOException e) {Debug.log(e);
								return null;}
				}				
		}
}
