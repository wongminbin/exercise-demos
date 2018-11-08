package com.wong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.wong.POJO.Test2;

/**
* @author HuangZhibin
* 
* 2018年5月14日 上午9:09:40
*/
public class Demo {

	@Test
	public void test() {
//		String json = "{\"address\":\"test\"}";
//		
//		JSON.parseObject(json, Test1.Test2.class);
		
		String json = "{\"age\":10, \"test2\" : {\"address\":\"test\"}}";
		POJO test1 = JSON.parseObject(json, POJO.class);
		System.out.println(JSON.toJSONString(test1));
	}
	
	@Test
	public void test1() throws Exception {
		Class<Test2> clazz = POJO.Test2.class;
		Object instance  = clazz.getDeclaredConstructors()[0].newInstance(POJO.class.newInstance());
		System.out.println(instance);
	}
	
	@Test
	public void test2() {
		String[] strs = StringUtils.split("asdfas, ", ",");
		System.out.println(strs.length);
		for (String str : strs) {
			System.out.println(str);
		}
	}
	
	@Test
	public void test3() {
		List<String> list = Arrays.asList("1", "2");
		
		List<String> temps = list.stream().filter(v->false).collect(Collectors.toList());
		
		for (String string : temps) {
			System.out.println(string);
		}
	}
	
	@Test
	public void test4() {
		MyList<String> list = new MyList<>();
		list.add("123a");
		list.add("123b");
		list.add("123c");
		
		list.setCurPage(1);
		
		System.out.println(JSON.toJSONString(list));
	}
	
	@Test
	public void test5() {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		System.out.println(calendar.getTime());
		
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		System.out.println(calendar.getTime());
		
		String str = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.CHINA);
		System.out.println(str);
	}
	
	
	public class MyList<E> extends ArrayList<E> {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1293119276280378441L;
		
		private int curPage;
		
		public MyList() {
			super();
		}
		
		public MyList(int initialCapacity) {
			super(initialCapacity);
		}
		
		public MyList(Collection<? extends E> c) {
			super(c);
		}
		
		public int getCurPage() {
			return curPage;
		}
		
		public void setCurPage(int curPage) {
			this.curPage = curPage;
		}
	}
}
