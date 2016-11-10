package com.atguigu.thread.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ThreadDemo07 {

	public static void main(String[] args) {
		//ExecutorService service = Executors.newFixedThreadPool(5);
		//ExecutorService service=Executors.newSingleThreadExecutor();
//		ExecutorService service=Executors.newCachedThreadPool();
//		Future<Integer> result = null;
//
//		try {
//			for (int i = 0; i < 20; i++) {
//				result = service.submit(new Callable<Integer>() {
//
//					@Override
//					public Integer call() throws Exception {
//						Thread.sleep(200);
//						System.out.println(Thread.currentThread().getName());
//						return new Random().nextInt(30);
//					}
//				});
//
//				System.out.println(result.get());
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			service.shutdown();
//
//		}
          testThread07();
	}
      public static void testThread07(){
    	         ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
    	         ScheduledFuture<Integer> schedule=null;
    	            try {
						for (int i = 0; i <50; i++) {
							schedule = service.schedule(new Callable<Integer>(){

								@Override
								public Integer call() throws Exception {
								System.out.println(Thread.currentThread().getName());
									return new Random().nextInt(50);
								}},2,TimeUnit.SECONDS);
							
							System.out.println(schedule.get());
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally{
						service.shutdown();
					}
    	         
      }
}
