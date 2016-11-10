package com.atguigu.thread.test;



public class TestThread {
	public static void main(String[] args) {
		Ticket t = new Ticket();

		Thread t1 = new Thread(t, "t1");
		Thread t2 = new Thread(t, "t2");
		Thread t3 = new Thread(t, "t3");
		t1.start();
		t2.start();
		t3.start();
	}

}

class Ticket implements Runnable {
	private int ticketNum = 30;

	public synchronized void maiPiao() throws Exception {
		if (ticketNum > 0) {
			Thread.sleep(200);
			System.out.println(Thread.currentThread().getName() + "卖出第："
					+ ticketNum-- + "票,还有" + ticketNum + "张票");
		}
	}

	@Override
	public void run() {

		for (int i = 0; i < 40; i++) {
			try {
				maiPiao();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}