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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

class StreamReader implements Callable {
		private Process proc;
		private BufferedReader in;
		private StringBuilder builder;
		private ILineListener lineListener;
		private IResultListener resultListener;

		private StreamReader(Process proc, ILineListener lineListener, IResultListener resultListener) {
				this.proc = proc;
				this.in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				builder = new StringBuilder("");
				this.lineListener = lineListener;
				this.resultListener = resultListener;
		}

		public synchronized static StreamReader newInstance(Process proc, ILineListener lineListener) {return new StreamReader(proc, lineListener, null);}

		public synchronized static StreamReader newInstance(Process proc, IResultListener resultListener) {return new StreamReader(proc, null, resultListener);}

		public synchronized static StreamReader newInstance(Process proc, ILineListener lineListener, IResultListener resultListener) {return new StreamReader(proc, lineListener, resultListener);}

		@Override
		public ShellResult call() {
				try {
						for (String line = in.readLine(); line != null; line = in.readLine()) {
								if (builder != null) builder.append(line + "\n");
								if (lineListener != null) lineListener.onNewLine(line);
						}
						in.close();
				} catch (IOException e) {Debug.log(e);}
				if (resultListener != null) resultListener.onResult(Convert.trimString(builder));
				try {
						int exitValue = proc.waitFor();
						
						proc.destroy();
						return ShellResult.newInstance(Convert.trimString(builder), exitValue);
				} catch (Exception e) {Debug.log(e);}
				return ShellResult.newInstance(null, 1);
		}
}
