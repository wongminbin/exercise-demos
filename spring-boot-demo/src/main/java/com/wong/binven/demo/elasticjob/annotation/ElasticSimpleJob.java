package com.wong.binven.demo.elasticjob.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by: HuangZhiBin
 * 2018年11月6日 下午3:22:10
 */

@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ElasticSimpleJob {

	/** 定时任务 */
	public String cron();
	
	/** 任务名称 */
	public String name() default "";
	
	/** 任务分片 */
	public int sharding() default 1;
	
	public String itemParameters() default "";

	public String parameter() default "";

	/** 任务记录的数据源 */
	public String dataSource() default "dataSource";
	
	public String description() default "";
	
	public boolean disabled() default false;

	public boolean overwrite() default true;
}
