package com.demo.metrics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codahale.metrics.Timer;
import com.codahale.metrics.Timer.Context;

@Aspect
@Component
public class ExecuteWithTimerMetricAspect {
	
	@Autowired
	private MetricsFacade metrics;
	
	@Pointcut("execution(@com.demo.metrics.WithTimerMetric * *(..))")
	public void anyMethodWithTimerMetric() {
	}
	
	@Around("anyMethodWithTimerMetric() && @annotation(withTimerMetric)")
    public Object executeWithTimer(ProceedingJoinPoint joinPoint, WithTimerMetric withTimerMetric) throws Throwable {
		Timer timer = metrics.getTimer(withTimerMetric.value());
		if (null == timer) {
			return joinPoint.proceed();
		}
		else {
			return executeWithTimer(joinPoint, timer);
		}
    }

	private Object executeWithTimer(ProceedingJoinPoint joinPoint, Timer timer)
			throws Throwable {
		Context context = timer.time();
		try {
			return joinPoint.proceed();
		} finally {
			context.stop();
		}
	}
}
