package com.atguigu.thread.test;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadDemo06 {

	public static void main(String[] args) {
		final MyQueue mq = new MyQueue();

		new Thread(new Runnable() {

			@Override
			public void run() {
				mq.set(new Random().nextInt(30));
			}
		}).start();;

		for (int i = 1; i <= 100; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					mq.get();

				}
			}, String.valueOf(i)).start();;
		}
	}

}

class MyQueue {
	private Object obj;
	ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public void set(Object obj) {

		readWriteLock.writeLock().lock();
		try {
			this.obj = obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readWriteLock.writeLock().unlock();

		}

	}

	public void get() {
		readWriteLock.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName() + "\t" + obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			readWriteLock.readLock().unlock();
		}
	}
}