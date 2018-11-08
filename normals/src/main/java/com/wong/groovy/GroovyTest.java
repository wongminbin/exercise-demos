package com.wong.groovy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.scripting.ScriptEvaluator;
import org.springframework.scripting.groovy.GroovyScriptEvaluator;
import org.springframework.scripting.support.StaticScriptSource;

import groovy.util.ResourceException;
import groovy.util.ScriptException;

/**
 * @author Huang zhibin
 * 
 *         2017年9月4日 下午1:53:41
 */
public class GroovyTest {
	
//		// Java 执行Groovy脚本的性能测评
//		// @see http://www.ithao123.cn/content-11099041.html
//		
//		// 2、通过groovyShell类直接执行脚本，例如：
//		Binding bind = new Binding();
//		bind.setVariable("str", "test");
//		GroovyShell shell = new GroovyShell(bind);
//		Object obj = shell.evaluate("return str");
//		System.out.println(obj);
//
//		// 3、通过groovyScriptEngine执行文件或者脚本，例如：
//
//		 GroovyScriptEngine engine = new GroovyScriptEngine("groovy");
//		 Object obj1 = engine.run("test.groovy","test");
//		 System.out.println(obj1);
//		// ​4、通过GroovyClassLoader来执行，例如：
//
//		 String script="";//groovy script
//		 ClassLoader parent = ClassLoader.getSystemClassLoader();
//		 GroovyClassLoader loader = new GroovyClassLoader(parent);
//		 Class<?> clazz = loader.parseClass(script);
//		 
//		 GroovyObject clazzObj = (GroovyObject)clazz.newInstance();
//		 loader.close();
//		 System.out.println(clazzObj.invokeMethod("test", "str"));
	
//		 GroovyDemo demo = new GroovyDemo();
//		 demo.repeat("1234");

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, 
		IOException, ResourceException, ScriptException {
		
		Map<String, Object> map = new HashMap<>();
		map.put("bean", new Test());
		map.put("method", "invoke");
		map.put("userId", 1204);
		map.put("requestDto", new TestDto());
		
		// spring
		StaticScriptSource script = new StaticScriptSource("bean.invoke(userId, requestDto)");
		ScriptEvaluator evaluator = new GroovyScriptEvaluator();
		Object result = evaluator.evaluate(script, map);
		System.out.println(result);
	}
}
