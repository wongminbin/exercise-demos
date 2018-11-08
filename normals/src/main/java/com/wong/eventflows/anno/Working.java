package com.wong.eventflows.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author HuangZhibin
* 
* 2018年7月3日 下午5:44:06
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Working {
	
	int order() default 0;
}