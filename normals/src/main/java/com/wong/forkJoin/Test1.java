package com.wong.forkJoin;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
* @author HuangZhibin
* 
* 2018年7月9日 上午10:59:05
*/
//public class Test1 extends RecursiveTask<List<String>> {
public class Test1 extends RecursiveAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3407433320122944401L;
	
	private List<String> list = null;

	public Test1(List<String> list) {
		this.list = list;
	}
	
	@Override
	protected void compute() {
		if (list.size() < 3) {
			list.forEach(v->System.out.println(v));
		} else {
			
			Test1 temp1 = new Test1(list.subList(0, list.size()/2));
			Test1 temp2 = new Test1(list.subList(list.size()/2, list.size()));
			
			temp1.fork();
			temp2.fork();
			
			
			temp1.join();
			temp2.join();
		}
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<String> list = Arrays.asList("a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7");
		List<String> list1 = Arrays.asList("b0", "b1", "b2", "b3", "b4", "b5", "b6", "b7");
		List<String> list2 = Arrays.asList("c0", "c1", "c2", "c3", "c4", "c5", "c6", "c7");
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(new Test1(list));
		pool.execute(new Test1(list2));
		pool.submit(new Test1(list1)).get();
//		pool.execute(new Test1(list1));
//		
//		pool.invoke(new Test1(list2));
		
		
		
//		pool.invoke(new MyTask<List<String>, String>(list) {
//			private static final long serialVersionUID = 6354844521927515410L;
//
//			@Override
//			protected String compute() {
//				List<String> list = getValue();
//				return null;
//			}
//			
//		});
	}
}
