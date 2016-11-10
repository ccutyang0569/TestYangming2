package com.atguigu.thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo01 {

	public static void main(String[] args) {
		final Ticket2 t = new Ticket2();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<40;i++){
					t.sale();
				}

			}
		},"AA").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<40;i++){
					t.sale();
				}

			}
		},"BB").start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0;i<40;i++){
					t.sale();
				}

			}
		},"CC").start();
	}

}

class Ticket2 {
	private int ticketNo = 30;
	Lock lock = new ReentrantLock();

	public void sale() {
		lock.lock();
		if (ticketNo > 0) {
			try {
				Thread.sleep(200);
				System.out.println(Thread.currentThread().getName() + "卖出:"
						+ ticketNo-- + "票,还有:"+ticketNo+"票");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

		}
	}

}