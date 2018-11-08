package com.wong.metrics;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

/**
 * @author HuangZhibin
 * 
 *         2018年8月10日 下午3:45:11
 */
public class MetricsTest {

	private MetricRegistry metrics;

	@Before
	public void before() {
		metrics = new MetricRegistry();

		ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
				.convertRatesTo(TimeUnit.SECONDS)
				.convertDurationsTo(TimeUnit.MILLISECONDS)
				.build();
		
		reporter.start(1, TimeUnit.SECONDS);
	}
	
	@Test
	public void test() {
		Timer requests = metrics.timer(MetricRegistry.name(getClass(), "test1", "test2"));
		requests.time();
		
		wait5Seconds();
	}
	
	public void wait5Seconds() {
		for (int i = 0; i < 10; i++) {
			new Thread(()->{
				Meter requests = metrics.meter("requests");
				requests.mark();
				for (int j = 0; j < 1000; j++) {
					new ArrayList<>();
				}
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {}
			}).start();
		}
		
		try {
			Thread.sleep(6 * 1000);
		} catch (InterruptedException e) {}
	}
}
