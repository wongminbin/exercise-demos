package com.wong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

/**
* @author HuangZhibin
* 
* 2018年8月7日 下午8:09:10
*/
public class SortedMerge {

	@Test
	public void test() {
		List<POJO> pojos = new ArrayList<>();
		for (int i = 0; i < 20; i++) {
			POJO pojo = new POJO();
			pojo.setAge(String.valueOf(i));
			pojo.setName(String.valueOf(i));
			pojo.setScore(RandomUtils.nextDouble(60, 100));
			
			pojos.add(pojo);
		}
		pojos.forEach(v->System.out.println(JSON.toJSONString(v)));
		pojos.stream().collect(Collectors.groupingBy(POJO::getScore));
		System.out.println("=============================================");
		
		List<POJO> results = compute(pojos, POJO::getScore, Ordered.DESC, (list)->{
			POJO merge = new POJO();
			merge.setName("合并");
			list.forEach(v->{
				merge.setScore(merge.getScore() + v.getScore());
			});
			return merge;
		}, 5);
		
		results.forEach(v->System.out.println(JSON.toJSONString(v)));
	}
	
	/**
	 * 
	 * 将src排序, 当src的数量size > max时, 返回新数组, 包含已排序的源数组中[0, max)
	 * 而[max, src.size)通过merge合并成一个新元素加入新数组
	 * 
	 * @author HuangZhibin
	 *
	 * @param src 源数据集合
	 * @param sorted 参与集合排序的字段
	 * @param order 集合排序规则
	 * @param merge 合并规则
	 * @param max 最大限制
	 * @return
	 */
	public static <T, K extends Comparable<K>> List<T> compute(List<T> src, Function<? super T, ? extends K> sorted, Ordered order, 
			Function<List<T>, T> merge, int max) {
		
		Collections.sort(src, (a, b)-> order.getScale() * sorted.apply(a).compareTo(sorted.apply(b)));
		
		max = Math.min(max, src.size());
		
		List<T> news = new ArrayList<>(max + 1);
		for (int i = 0; i < max; i++) {
			news.add(src.get(i));
		}
		
		if (max < src.size()) {
			List<T> merges = src.subList(max, src.size());
			T t = merge.apply(merges);
			news.add(t);
		}
		
		return news;
	}
	
	public static enum Ordered {
		ASC(1),
		DESC(-1);
		
		private int scale;
		
		private Ordered(int scale) {
			this.scale = scale;
		}
		
		public int getScale() {
			return scale;
		}
	}
}
