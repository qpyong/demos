package org.qpy.demos.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class CountDownLatchDemo
{
	private CountDownLatch	latch;
	private final int		threadNum;

	public CountDownLatchDemo(int threadNumbers)
	{
		this.threadNum = threadNumbers;
		latch = new CountDownLatch(threadNumbers);
	}

	public List<Integer> doTasks()
	{
		ExecutorService es = Executors.newFixedThreadPool(threadNum);
		List<Future<Integer>> list = new ArrayList<Future<Integer>>();
		try
		{
			System.out.println("====开始分线程执行....");
			for (int i = 0; i < threadNum; i++)
				list.add(es.submit(new Worker(latch)));
			latch.await();
			System.out.println("====分线程执行完毕....");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		List<Integer> ints = new ArrayList<Integer>();
		for (Future<Integer> f : list)
			try
			{
				ints.add(f.get());
			} catch (InterruptedException | ExecutionException e)
			{
				e.printStackTrace();
			}
		es.shutdown();
		return ints;
	}
}
