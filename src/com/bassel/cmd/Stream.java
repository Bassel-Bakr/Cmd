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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Stream implements Runnable
{
	private BufferedReader reader;
	private Listeners.LineListener listener;
	private StringBuilder result;

	private Stream(InputStream stream, Listeners.LineListener listener, StringBuilder result)
	{
		reader = new BufferedReader(new InputStreamReader(stream));
		this.listener = listener;
		this.result = result;
	}
	
	public static Stream newInstance(InputStream stream, Listeners.LineListener listener, StringBuilder result)
	{
		return new Stream(stream, listener, result);
	}

	@Override
	public void run()
	{
		try
		{
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				if (result != null) result.append(line + "\n");
				if (listener != null) listener.onLine(line);
			}
			reader.close();
		}
		catch (IOException e)
		{}
	}
}
