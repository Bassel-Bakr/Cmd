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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exec {
		private static ExecutorService service;

		static {
				service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		}

		public static void execute(Runnable runnable) {
				service.execute(runnable);
		}

		public static void execute(Runnable[] runnables) {
				int size = runnables.length;
				for (int i = 0; i < size; i++)
						service.execute(runnables[i]);
		}

		public static void execute(List<Runnable> runnables) {
				int size = runnables.size();
				for (int i = 0; i < size; i++)
						service.execute(runnables.get(i));
		}

		public static Future<StringBuilder> submit(Runnable runnable, StringBuilder result) {
				return service.submit(runnable, result);
		}

		public static Future<ShellResult> submit(Callable<ShellResult> callable) {
				return service.submit(callable);
		}

		public synchronized static Future<String>[] submit(Callable<String>[] callables) {
				int size = callables.length;
				Future<String>[] futures = new Future[size];
				
				for (int i = 0; i < size; i++)
						futures[i] = service.submit(callables[i]);

				return futures;
		}

		public static List<Future<String>> submit(final List<Callable<String>> callables) {
				return new ArrayList<Future<String>>()
				{{
								int size = callables.size();
								for (int i = 0; i < size; i++)
										add(service.submit(callables.get(i)));
						}};
		}

		public static void shutdown() {
				service.shutdown();
		}
}
