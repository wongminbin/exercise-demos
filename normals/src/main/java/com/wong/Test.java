package com.wong;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

/**
* @author HuangZhibin
* 
* 2018年2月28日 下午3:16:59
*/
public class Test {
	
	private static String RANDOM_STR = "0147852369qazxswedcvfrtgbnhyujmkiolpQWERTYUIOPASDFGHJKLMNBVCXZ_+-&=!@#$%^*()";
	
	public static void main(String[] args) {
//		BigDecimal zero = BigDecimal.TEN;
		
//		BigDecimal zero = new BigDecimal(10.01d);
//		BigDecimal temp = new BigDecimal(10.01);
//		yBu84Kx*s90S+$N$=48=&vXj x=o6!icQx_y8PN%-i7HGtKbA
//		System.out.println("===================="+(zero == temp));
//		System.out.println("===================="+(zero.equals(temp)));
//		System.out.println("===================="+(zero.compareTo(temp) == 0));
		
		System.out.println(RandomStringUtils.random(24, RANDOM_STR));
	}
	
	public static void insert(List<Integer> arr, int x1, int x2) {
		if (x1 >= x2) {
			throw new IllegalArgumentException("x1 必须小x2");
		}
		boolean insert = true;
		
		// 计算有多少个区间
		int rows = arr.size()/2;
		// 插入的区间必须大于当前循环的区间的最大值和小于下一区间的最小值
		for (int i = 0; i < rows; i++) {
			int temp1 = arr.get(2*i);
			int temp2 = arr.get(2*i+1);
			if (between(temp1, temp2, x1) || between(temp1, temp2, x2)) {
				insert = false;
				break;
			}
			
		}
		if (insert) {
			arr.add(x1);
			arr.add(x2);
			Collections.sort(arr);
		}
	}
	
	/**
	 * 判断y是否在区间[x1, x2]内,是返回true,否返回false
	 * @author HuangZhibin
	 *
	 * @param x1
	 * @param x2
	 * @param y
	 * @return
	 */
	public static boolean between(int x1, int x2, int y) {
		return (y >= x1) && (y <= x2);
	}
}
