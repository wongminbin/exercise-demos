package com.wong.exp4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author HuangZhibin
* 
* 2018年9月11日 上午9:47:12
*/

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Var {

}
