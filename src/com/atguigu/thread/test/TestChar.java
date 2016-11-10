package com.atguigu.thread.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestChar {

	@Test
	public void test01() {
		for (int i = 65; i < 91; i++) {
			System.out.println((char) i+"\t");
			
		}
	}

}
