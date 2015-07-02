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

		public synchronized static void execute(Runnable runnable) {
				service.execute(runnable);
		}

		public synchronized static void execute(Runnable[] runnables) {
				for (Runnable runnable : runnables)
						service.execute(runnable);
		}

		public synchronized static void execute(List<Runnable> runnables) {
				for (Runnable runnable : runnables)
						service.execute(runnable);
		}

		public synchronized static Future<StringBuilder> submit(Runnable runnable, StringBuilder result) {
				return service.submit(runnable, result);
		}

		public synchronized static Future<ShellResult> submit(Callable<ShellResult> callable) {
				return service.submit(callable);
		}

		public synchronized static Future<String>[] submit(Callable<String>[] callables) {
				Future<String>[] futures = new Future[callables.length];

				for (int i = 0; i < callables.length; i++)
						futures[i] = service.submit(callables[i]);

				return futures;
		}

		public synchronized static List<Future<String>> submit(final List<Callable<String>> callables) {
				return new ArrayList<Future<String>>()
				{{
								for (Callable<String> callable : callables)
										add(service.submit(callable));
						}};
		}

		public synchronized static void shutdown() {
				service.shutdown();
		}
}
