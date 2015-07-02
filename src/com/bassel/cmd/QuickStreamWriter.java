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
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

class QuickStreamWriter {
		public synchronized static <T> void write(Process proc, T cmds) {
				OutputStream writer = proc.getOutputStream();
				try {
						if (cmds instanceof String)
								writer.write((cmds + "\n").getBytes());
						else if (cmds instanceof String[]) {
								String[] commands = (String[]) cmds;
								int size = commands.length;
								for (int i = 0; i < size; i++) 
										writer.write((commands[i] + "\n").getBytes());
						} else if (cmds instanceof List) {
								List<String> commands = (List<String>) cmds;
								int size = commands.size();
								for (int i = 0; i < size; i++) 
										writer.write((commands.get(i) + "\n").getBytes());
						}
						writer.flush();
						writer.close();
				} catch (IOException e) {Debug.log(e);}
		}
}
