package com.wong.redis.search;

import com.wong.redis.search.annotation.RedisEntity;
import com.wong.redis.search.annotation.RedisField;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
* @author HuangZhibin
* 
* 2018年8月25日 下午3:38:52
*/

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RedisEntity(index="person-index")
public class Person {

	@RedisField(weight=5.0)
	private String name;
	
	private String address;
	
	private int age;

}
