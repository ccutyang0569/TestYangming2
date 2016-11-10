package com.atguigu.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo03 {

	/**
	 * 题目：现在两个线程，可以操作同一个变量，实现一个线程对该变量加1，一个线程对该变量减1， 实现交替，来10轮，变量初始值为零。
	 * 
	 * @author zhouyang 1 线程 操作 资源 2 高内聚 低耦合 3 封装 4 虚假唤醒
	 */
	public static void main(String[] args) {
		final ShareData sd = new ShareData();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
			for (int i = 0; i < 10; i++) {
				   sd.add();
			}
				
			}},"AA").start();
		new Thread(new Runnable(){

			@Override
			public void run() {
			for (int i = 0; i < 10; i++) {
				   sd.sub();
			}
				
			}},"BB").start();
	}

	

	// public static void main(String[] args) {
	// final Number n = new Number();
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// for (int i = 0; i < 10; i++) {
	// try {
	// n.add();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// }
	// },"AA").start();
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// for (int i = 0; i < 10; i++) {
	// try {
	// n.sub();;
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	// }
	// },"BB").start();
	//
	// }
	//
	// }
	//
	// class Number {
	// private int num = 0;
	//
	// public synchronized void add() throws Exception {
	//
	// while (num != 0) {
	// wait();
	// }
	// ++num;
	// System.out.println(Thread.currentThread().getName() + "---num数值:" + num);
	// notifyAll();
	// }
	//
	// public synchronized void sub() throws Exception {
	// while (num == 0) {
	// wait();
	// }
	// --num;
	// System.out.println(Thread.currentThread().getName() + "---num数值:" + num);
	// notifyAll();
	// }
}
class ShareData {

	private int number = 0;

	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();

	public void add(){
		lock.lock();
		try {
			while (number != 0) {
				condition.await();
			}
			++number;
			System.out.println(Thread.currentThread().getName() + ":::"
					+ number);
			condition.signalAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void sub() {
		lock.lock();
		try {
			while (number == 0) {
				condition.await();
			}
			--number;
			System.out.println(Thread.currentThread().getName() + ":::"
					+ number);
			condition.signalAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}