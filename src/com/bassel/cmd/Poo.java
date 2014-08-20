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
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Pool
{
	private static Pool instance;
	private static ExecutorService service;

	private Pool()
	{
		service = Executors.newCachedThreadPool(this);
	}

	public static Pool getInstance()
	{
		if (instance != null) return instance;
		return new Pool();
	}

	public void execute(Runnable runnable)
	{
		service.execute(runnable);
	}

	public void execute(Runnable[] runnable)
	{
		for (Runnable r : runnable)
		{
			service.execute(r);
		}
	}

	public void execute(List<Runnable> runnable)
	{
		for (Runnable r : runnable)
		{
			service.execute(r);
		}
	}

	public void shutdown()
	{
		service.shutdown();
	}

}
