package org.qpy.demos.concurrency;

import java.util.List;
public class CountDownLatchTest
{
	/**
	 * 多线程执行多个任务
	 * @param num
	 */
	static void executeInThread(int num)
	{
		CountDownLatchDemo demo = new CountDownLatchDemo(num);
		List<Integer> list = demo.doTasks();
		for (Integer i : list)
			System.out.println(i);
	}

	/**
	 * 顺序执行多个任务
	 * @param num
	 */
	static void executeInSequence(int num)
	{
		for (int i = 0; i < num; i++)
			try
			{
				System.out.println(new Worker(null).call());
			} catch (Exception e)
			{
				e.printStackTrace();
			}
	}

	public static void main(String[] args)
	{
		long start = System.currentTimeMillis();
		executeInThread(10000000);
		System.out.println("结果如下：");
		System.out.println("总共耗时" + (System.currentTimeMillis() - start) + "ms");
	}
}
