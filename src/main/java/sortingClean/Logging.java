package sortingClean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Aspect
public class Logging {
    private static long startTime;
    private static final Map<String, Integer> executionCounts = new HashMap<>();

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

        // Update the execution count
        executionCounts.merge(name, 1, Integer::sum);

        // Retrieve the execution count for the current sorting function
        int count = executionCounts.get(name);

        // Print the execution time and count
        System.out.printf("Function sort in %s ran %d times and took in total %d ms\n", name, count, executionTime);

    }
}
