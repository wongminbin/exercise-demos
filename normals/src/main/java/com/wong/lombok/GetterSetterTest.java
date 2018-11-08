package com.wong.lombok;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* @author Huang zhibin
* 
* 2017年10月31日 下午4:05:22
*/

// 为字段生成 getter,setter 方法, 标记到类上表明为所有字段生成
@Getter
@Setter

// 生成 toString 方法, 默认打印所有非静态字段
@ToString

// 为所有字段生成构造函数
@AllArgsConstructor
// 无参构造函数
@NoArgsConstructor
// 为未初始化的 final 字段和使用 @NonNull 标注的字段生成构造函数
// 当使用staticName标识后，有参构造函数变为private并生成一个静态的生成函数
@RequiredArgsConstructor//(staticName="create")

// 生成 equals和hashCode 方法
@EqualsAndHashCode(of = {"age"})
public class GetterSetterTest {

	private int age;
	
	// 使用 @NonNull 注解修饰的字段 通过 set 方法设置时如果为 null, 将抛出 NullPointerException
	@NonNull
	private String name;
	
	public String getName() {
		return name + "0000000";
	}
	
	public void setName(String name) {
		this.name = "111111111111" + name;
	}
	
	public static void main(String[] args) {
		GetterSetterTest test = new GetterSetterTest();
		
		test = new GetterSetterTest("Wangwu");
		
		test = new GetterSetterTest(0, "Zhangsan");
		
		test.setAge(1);
		test.setName("Lisi");
		
		System.out.println(test.getName());
		System.out.println(test.toString());
		
		//GetterSetterTest.create("");
	}
}
