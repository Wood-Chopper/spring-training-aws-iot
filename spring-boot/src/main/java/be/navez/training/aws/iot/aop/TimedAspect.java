package be.navez.training.aws.iot.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Component
public class TimedAspect {

	@Around("@annotation(timed)")
	public Object timedAspect(ProceedingJoinPoint joinPoint, Timed timed) throws Throwable {
		//stuff
		final long start = System.currentTimeMillis();
		final Object proceed = joinPoint.proceed();
		final long end = System.currentTimeMillis();
		System.out.println(end - start);
		// other stuff
		return proceed;
	}

	@After("@annotation(timed)")
	public void after(Timed timed) {
		timed.description();
		System.out.println("after");
	}

	@Before("@annotation(timed)")
	public void before(Timed timed) {
		System.out.println("before");
	}
}
