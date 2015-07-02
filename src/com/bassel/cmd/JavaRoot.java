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

import java.util.concurrent.Future;

public class JavaRoot {
		private static String packageName;
		private static String className;
		private static boolean isSystemApp;

		private StringBuilder strgs;

		private JavaRoot(String packageName, String className, boolean isSystemApp) {
				this.packageName = packageName;
				this.className = className;
				this.isSystemApp = isSystemApp;
		}

		public static JavaRoot newInstance(String packageName, String className, boolean isSystemApp) {
				return new JavaRoot(packageName, className, isSystemApp);
		}

		public Future<ShellResult> execute(String... args) {
				strgs = new StringBuilder();
				strgs.append("");
				if (!args.equals(null)) {
						for (String arg : args) {
								strgs.append(" \"" + arg + "\"");
						}
				}
				return Cmd.SU.ex(new String[]
												 {
														 "cmd_dir=/system/bin",
														 String.format("app=%s", packageName),
														 "export CLASSPATH=`pm path $app`",
														 String.format("exec app_process $cmd_dir %s %s", className , strgs.toString())
												 });
		}
}
