package com.wong.metrics;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SharedMetricRegistries;
import com.codahale.metrics.annotation.Timed;

import io.astefanutti.metrics.aspectj.Metrics;

/**
* @author HuangZhibin
* 
* 2018年8月11日 下午7:12:53
*/

@Metrics(registry = "registryName")
public class TimedMethod {
	
	MetricRegistry registry = SharedMetricRegistries.getOrCreate("registryName");

    ConsoleReporter reporter = ConsoleReporter.forRegistry(registry)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS).build();

    @Before
    public void init() {
    	reporter.start(1, TimeUnit.SECONDS);
    }

	@Test
	@Timed(name="timed-method")
	public void test() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		
		for (int i = 0; i < 10; i++) {
			new Thread(() -> test2()).start();
		}
		
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {}
	}
	
	@Timed(name="timed-method-1")
	public void test2() {
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
	}
}
