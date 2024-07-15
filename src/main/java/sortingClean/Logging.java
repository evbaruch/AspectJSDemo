package sortingClean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Timer;
import java.util.TimerTask;

@Aspect
public class Logging {
    private static long startTime;

    @Pointcut("execution(* *.sort(..))")
    private void selectAll(){}

    @Before("selectAll()")
    public void beforeAdvice(){
        // Capture the start time before the sort method execution
        startTime = System.currentTimeMillis();
    }

    @After("selectAll()")
    public void afterAdvice(JoinPoint joinPoint){
        // Calculate the execution time
        long executionTime = System.currentTimeMillis() - startTime;
        String name = joinPoint.getTarget().getClass().getSimpleName();
        System.out.printf("Function sort in %s ran and took in total %d ms\n", name, executionTime);
    }
}
