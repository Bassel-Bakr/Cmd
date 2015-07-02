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

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

public class Convert {
		public synchronized static ShellResult future2present(Future<ShellResult> future) {
				try {
						return future.get();
				} catch (Exception e) {Debug.log(e);
						return null;}
		}

		public synchronized static List<String> array2list(String[] array) {
				return Arrays.asList(array);
		}

		public synchronized static String array2string(String[] array) {
				StringBuilder string = new StringBuilder();
				int size = array.length;
				for (int i = 0; i < size; i++)
						string.append(array[i] + "\n");
				if (string.length() > 1)
						string.deleteCharAt(string.length());
				return string.toString();
		}

		public synchronized static String[] list2array(List<String> list) {
				return list.toArray(new String[list.size()]);
		}

		public synchronized static String list2string(List<String> list) {
				StringBuilder string = new StringBuilder();
				int size = list.size();
				for (int i = 0; i < size; i++)
						string.append(list.get(i) + "\n");
				if (string.length() > 1)
						string.deleteCharAt(string.length());
				return string.toString();
		}

		public synchronized static String[] string2array(String string) {
				return string.split("\n");
		}

		public synchronized static List<String> string2list(String string) {
				return array2list(string.split("\n"));
		}

		public synchronized static String trimString(StringBuilder string) {
				int len = string.length();
				if (len > 1)
						string.deleteCharAt(len - 1);
				return string.toString();
		}
}
