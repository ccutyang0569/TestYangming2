package com.atguigu.thread.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadDemo05 {

	/**
	 * 题目：三个线程，要求实现按序访问,A>B>C...... A打印5次，B打印10次，C打印15次 接着按照上述同样的顺序，再来
	 * A打印5次，B打印10次，C打印15次 接着按照上述同样的顺序，再来 。。。。。。来20轮
	 * 
	 * @方法名: main
	 * @功能描述: TODO(这里用一句话描述这个方法的作用)
	 * @param args
	 * @作者 YangMing
	 * @日期 2016年11月9日
	 */
	public static void main(String[] args) {
		final ShareData2 sd=new ShareData2();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				 for(int i=1;i<=20;i++){
					 try {
						sd.printA(i);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				
			}},"A").start();;
		new Thread(new Runnable(){

			@Override
			public void run() {
				 for(int i=1;i<=20;i++){
					 try {
						sd.printB(i);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				
			}},"B").start();;
		new Thread(new Runnable(){

			@Override
			public void run() {
				 for(int i=1;i<=20;i++){
					 try {
						sd.printC(i);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				
			}},"C").start();
	}

	
}
class ShareData2 {
	private int number = 1;
	Lock lock = new ReentrantLock();
	Condition condition1 = lock.newCondition();
	Condition condition2 = lock.newCondition();
	Condition condition3 = lock.newCondition();

	public void printA(int totalCount) throws Exception {
		lock.lock();
		try {
			while (number != 1) {
				condition1.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"-------"+String.valueOf(totalCount));
			}
			number=2;
			condition2.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void printB(int totalCount)throws Exception {
		lock.lock();
		try {
			while (number != 2) {
				condition2.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"-------"+String.valueOf(totalCount));
			}
			number=3;
			condition3.signal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void printC(int totalCount) throws Exception {
		lock.lock();
		try {
			while (number != 3) {
				condition3.await();
			}
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"-------"+String.valueOf(totalCount));
			}
			number=1;
			condition1.signal();
			System.out.println();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}