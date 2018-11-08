package com.wong.redis.search.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author HuangZhibin
* 
* 2018年8月25日 下午3:41:38
*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RedisField {

//	String name() default "";
	
	double weight() default 1.0;
	
	boolean sort() default false;
}
