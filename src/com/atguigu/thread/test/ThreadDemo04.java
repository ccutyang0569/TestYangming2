package com.atguigu.thread.test;

public class ThreadDemo04 {
	// 题目：判断出现打印苹果还是android

	// 1 标准构成，先打印苹果还是android？
	// 2 新增Thread.sleep(4000),先打印苹果还是android？
	// 3 新增Hello，先打印苹果还是hello？
	// 4 有两部手机，先打印苹果还是android？
	// 5 两个静态同步方法，有一部手机，先打印苹果还是android？
	// 6 两个静态同步方法，有2部手机，先打印苹果还是android？
	// 7 1个普通同步方法，1个静态同步方法,有1部手机，先打印苹果还是android？
	// 8 1个普通同步方法，1个静态同步方法,有2部手机，先打印苹果还是android？
	public static void main(String[] args) {
		final Phone p = new Phone();
		final Phone p2 = new Phone();

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					p.getAnriod();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "AA").start();
		;
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					p.getIOS();
					//p.hello();
					//p2.getIOS();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, "BB").start();

	}

}

class Phone {

	public  synchronized void getIOS() throws Exception {

		System.out.println("----IOS--------");
	}

	public static synchronized void getAnriod() throws Exception {
          Thread.sleep(2000);
		System.out.println("------Anriod-----------");
	}
	
	public  void hello(){
		System.out.println("-------hello");
	}
}