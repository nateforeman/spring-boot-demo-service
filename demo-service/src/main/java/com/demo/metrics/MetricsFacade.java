package com.demo.metrics;

import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.SlidingTimeWindowReservoir;
import com.codahale.metrics.Timer;

@Component
public class MetricsFacade {
	
	private final MetricRegistry registry;
	private final ConsoleReporter reporter;
	
	public MetricsFacade() {
		this.registry = new MetricRegistry();
		
		this.reporter = ConsoleReporter.forRegistry(registry)
	            .convertRatesTo(TimeUnit.MINUTES)
	            .convertDurationsTo(TimeUnit.MILLISECONDS)
	            .formattedFor(Locale.US)
	            .formattedFor(TimeZone.getTimeZone("UTC"))
	            .build();
		this.reporter.start(1, TimeUnit.MINUTES);
	}
	
	public Timer getTimer(String name) {
		Timer result = registry.getTimers().get(name);
		if (null == result) {
			result = registerTimer(name);
		}
		return result;
	}
	
	private Timer registerTimer(String name) {
		System.out.println("Registering Timer Metric: " + name);
		return registry.register(name, new Timer(new SlidingTimeWindowReservoir(1, TimeUnit.MINUTES)));
	}
}
