package com.wong.binven.demo.config;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import com.wong.binven.demo.core.Log;

/**
 * create by: HuangZhiBin
 * 2018年11月1日 上午11:37:36
 */
public class ConfigBootstrap implements EnvironmentPostProcessor, Ordered, Log {

	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
		String[] profiles = environment.getActiveProfiles();
		getLogger().info("profiles ===== {}", Arrays.toString(profiles));
		String[] profiles2 = environment.getDefaultProfiles();
		getLogger().info("profiles ===== {}", Arrays.toString(profiles2));
		
		MutablePropertySources propertySources = environment.getPropertySources();
		propertySources.forEach(p->{
			getLogger().info("properties: key == {}, value == {}", p.getName(), p.getSource());
		});
		
		// 加载远程配置 http、database,允许动态修改属性
		Map<String, String> map = new ConcurrentHashMap<>();
		map.put("custom.test", "test-value");
		map.put("custom.address", "china");
		
		ConfigSource source = new ConfigSource("custom", map);
		environment.getPropertySources().addLast(source);
	}

	/**
	 * <pre>值越小，优先级越高</pre>
	 */
	@Override
	public int getOrder() {
		// +1 保证application.propertie里的内容能覆盖掉本配置文件中同名的值
        return ConfigFileApplicationListener.DEFAULT_ORDER + 1;
	}
	
}
