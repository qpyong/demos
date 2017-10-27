package org.qpy.demos.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
public class Worker implements Callable<Integer>
{
	private CountDownLatch latch;

	public Worker(CountDownLatch latch)
	{
		this.latch = latch;
	}

	@Override
	public Integer call() throws Exception
	{
		try
		{
			System.out.println(Thread.currentThread().getName() + " 正在执行...");
			int value = ThreadLocalRandom.current().nextInt(20);
			Thread.sleep(value * 1000 * 2);
			System.out.println(Thread.currentThread().getName() + " 正在完毕...");
			return value;
		} catch (Exception e)
		{
			throw e;
		} finally
		{
			if (latch != null)
				latch.countDown();
		}
	}
}
