package com.wong.genericity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
* @author Huang zhibin
* 
* 2017年11月13日 下午4:55:36
*/
public class Test {

	private List<String> list;
	
	public static void main(String[] args) throws Exception {
		
		Field field = Test.class.getDeclaredField("list");
		Type type0 = field.getType();
		System.out.println(type0.getTypeName());
		Type type1 = field.getGenericType();
		if (type1 instanceof ParameterizedType) {
			ParameterizedType temp = (ParameterizedType)type1;
			System.out.println(temp.getRawType().getTypeName());
			for (Type type : temp.getActualTypeArguments()) {
				System.out.println(type.getTypeName());
			}
		}

		// ========================================================//
		// ========================================================//

		// getGenericReturnType保存泛型类型的。
		// getReturnType得到的参数列表Type对象时不保存泛型类型的。
		
		Method method = Test.class.getDeclaredMethod("getList");
		Class<?> returnType = method.getReturnType();
		Type returnType2 = method.getGenericReturnType();
		
		System.out.println(returnType.getName());
		System.out.println(returnType2.getTypeName());
		
		// ========================================================//
		// ========================================================//
		
		// getGenericParameterTypes保存泛型类型的。
		// getParameterTypes得到的参数列表Type对象时不保存泛型类型的。
		
		Method method2 = Test.class.getDeclaredMethod("setList", List.class);
		
		Class<?>[] parameterTypes = method2.getParameterTypes();
		Type[] parameterTypes2 = method2.getGenericParameterTypes();
		for (Class<?> class1 : parameterTypes) {
			System.out.println(class1.getName());
		}
		
		for (Type type : parameterTypes2) {
			System.out.println(type.getTypeName());
		}
	}
	
	public List<String> getList() {
		return list;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}
}
