package com.atguigu.test.spring;

import org.apache.log4j.Logger;
import org.junit.Test;

public class StringTest {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StringTest.class);

	@Test
	public void test01() {

		String s1 = "java";
		String s2 = "ja";
		String s3 = "va";

		logger.info(s1 == "java");
		logger.info(s1 == "ja" + s3);
		logger.info(s1 == s2 + s3);
		// java中多态的理解:父类的引用纸箱子类的实例,
	}
}
