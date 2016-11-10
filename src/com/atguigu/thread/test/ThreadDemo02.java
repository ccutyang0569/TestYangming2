package com.atguigu.thread.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo02 {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException {
		Ticket03 t03 = new Ticket03();
		FutureTask<Integer> f = new FutureTask<Integer>(t03);
		new Thread(f).start();
		Integer fg = f.get();
		System.out.println(fg);

	}
}

class Ticket03 implements Callable<Integer> {
	private Integer ticketNo = 30;

	@Override
	public Integer call() throws Exception {

		Thread.sleep(200);
		Integer sale = sale();

		return sale;
	}

	public Integer sale() {

		if (ticketNo > 0) {
			ticketNo--;
		}

		return ticketNo;
	}
}