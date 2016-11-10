package com.atguigu.test;

public class MaiPiao implements Runnable {
	int count = 100;

	public static void main(String[] args) {
              MaiPiao piao = new MaiPiao();
              piao.run();
	}

	@Override
	public void run() {
		synchronized (MaiPiao.class) {
                maipiao01();
                maipiao02();
                maipiao03();
		}

	}

	public int maipiao01() {

		return count--;

	}

	public int maipiao02() {
		return count--;
	}

	public int maipiao03() {

		return count--;
	}
}
