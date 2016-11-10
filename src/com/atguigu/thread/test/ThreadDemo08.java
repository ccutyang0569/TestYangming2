package com.atguigu.thread.test;

public class ThreadDemo08 {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		final Customer customer = new Customer(clerk);
		final Productor productor = new Productor(clerk);

		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					System.out.println("生产者开始生产产品");
					try {
						Thread.sleep((int) Math.random() * 1000);
						productor.getClerk().addProduct();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
		;
		new Thread(new Runnable() {

			@Override
			public void run() {

				while (true) {
					System.out.println("消费之开始购买产品");
					try {
						Thread.sleep((int) Math.random() * 1000);
						customer.getClerk().getProduct();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
		;
	}

}

class Clerk {
	private int product = 0;

	public synchronized void addProduct() throws Exception {
		if (product >= 20) {
			wait();
		} else {
			product++;
			System.out.println("生产者生产了第:" + product + "产品");
			notifyAll();
		}
	}

	public synchronized void getProduct() throws InterruptedException {
		if (product <= 0) {
			wait();
		} else {
			product--;
			System.out.println("消费者消费了第:" + product + "产品");
			notifyAll();
		}
	}

}

class Customer {
	public Clerk getClerk() {
		return clerk;
	}

	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}

	Clerk clerk;

	public Customer(Clerk clerk) {
		super();
		this.clerk = clerk;
	}

}

class Productor {
	public Clerk getClerk() {
		return clerk;
	}

	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}

	Clerk clerk;

	public Productor(Clerk clerk) {
		super();
		this.clerk = clerk;
	}

}