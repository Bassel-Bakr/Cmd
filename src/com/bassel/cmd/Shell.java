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
import java.util.ArrayList;
import java.util.concurrent.Future;

/*
 * @hide
 *
 */
 public class Shell {

		public synchronized <T> Future<ShellResult> start(String cmd, T commands, File path, boolean redirectErrorStream, ILineListener lineListener, IResultListener resultListener) throws IOException {
				ProcessBuilder builder = new ProcessBuilder(cmd).redirectErrorStream(redirectErrorStream);
				if (path != null)
						builder.directory(path);
				Process proc = builder.start();

				QuickStreamWriter.write(proc, commands);
				try {
						QuickStreamWriter.write(proc, "exit");
				} catch (Exception e) {Debug.log(e);
				e.printStackTrace();}
				return Exec.submit(StreamReader.newInstance(proc, lineListener, resultListener));
		}

		/** @hide */
		public synchronized PrintWriter startInteractive(final String cmd, File path, boolean redirectErrorStream, ILineListener lineListener, IResultListener resultListener) throws IOException {
				ProcessBuilder builder = new ProcessBuilder(cmd).redirectErrorStream(redirectErrorStream);
				if (path != null)
						builder.directory(path);
				Process proc = builder.start();

				QuickStreamWriter.write(proc, new ArrayList<String>(){{add(cmd);}});
				Exec.submit(StreamReader.newInstance(proc, lineListener, resultListener));
				return new PrintWriter(proc.getOutputStream(), true);
		}
}
