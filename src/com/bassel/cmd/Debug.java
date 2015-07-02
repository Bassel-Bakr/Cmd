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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Debug {
		private static boolean DEBUG;
		private static Logger logger;

		static {
				DEBUG = true;
				logger = Logger.getLogger("Cmd");
		}

		public static boolean debug() {
				return DEBUG;
		}

		public static void debug(boolean DEBUG) {
				Debug.DEBUG = DEBUG;
		}

		public static void log(String command) {
				if (!DEBUG) return;
				logger.log(Level.WARNING, command);
		}

		public static void log(String TAG, String command) {
				if (!DEBUG) return;
				logger.log(Level.WARNING, command);
		}

		public static void log(String[] commands) {
				if (!DEBUG) return;
				int size = commands.length;
				for (int i = 0; i < size; i++)
						logger.log(Level.WARNING, commands[i]);
		}

		public static void log(List<String> commands) {
				if (!DEBUG) return;
				int size = commands.size();
				for (int i = 0; i < size; i++)
						logger.log(Level.WARNING, commands.get(i));
		}

		public static void log(Throwable t) {
				if (!DEBUG) return;
				logger.log(Level.WARNING, t.getMessage(), t);
		}

		public static void log(String TAG, Throwable t) {
				if (!DEBUG) return;
				Logger.getLogger(TAG).log(Level.WARNING, t.getMessage(), t);
		}
}
