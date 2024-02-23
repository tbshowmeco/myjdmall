package com.showmeco.myjdmall.product;

import org.junit.jupiter.api.Test;

/**
 * 说明:
 *
 * @Author: @showmeco
 * @Date: 2024/2/8 10:39
 */
public class ThreadTest {


	@Test
	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				System.out.println(i + " ****");
			}
		}, "mythread1").start();
	}
}
