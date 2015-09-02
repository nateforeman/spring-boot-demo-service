package com.demo;

import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;

@SpringBootApplication
public class Application {

	private final MetricRegistry registry;
	private final ConsoleReporter reporter;

	public Application() {
		registry = new MetricRegistry();
		reporter = ConsoleReporter.forRegistry(registry)
                .convertRatesTo(TimeUnit.MINUTES)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .formattedFor(Locale.US)
                .formattedFor(TimeZone.getTimeZone("UTC"))
                .build();
		reporter.start(1, TimeUnit.MINUTES);
	}
	
	@Bean
	@Qualifier("com.demo.metrics")
	public MetricRegistry getMetricsRegistry() {
		return registry;
	}
	
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(Application.class);
    	app.setShowBanner(false);
    	app.run(args);
    }
}