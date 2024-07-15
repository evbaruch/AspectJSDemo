package sortingClean;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class Logging {
    private static long startTime;
    private static final Map<String, ExecutionStats> executionStatsMap = new HashMap<>();
    private static long totalExecutionTime = 0;

    // execution(* *..*.sort(..)) breaks down as follows:
    // execution: the pointcut is based on the execution of a method
    // *: the return type of the method is not specified
    // *..*: the method can be in any package - breaks down as follows:
    // *: the method can be in any package
    // ..: the method can be in any subpackage
    // *.sort: the method must be named sort
    // (..): the method can take any number of arguments of any type
    @Pointcut("execution(* *..*.sort(..))")
    private void selectAll(){}

    @Before("selectAll()")
    public void beforeAdvice(){
        if (startTime == 0) { // Initialize startTime at the very first sort operation
            startTime = System.currentTimeMillis();
        }
    }

    @After("selectAll()")
    public void afterAdvice(JoinPoint joinPoint){
        long currentTime = System.currentTimeMillis();
        long executionTime = currentTime - startTime;
        startTime = currentTime; // Reset startTime for the next operation
        String name = joinPoint.getTarget().getClass().getSimpleName();
        addExecution(name, executionTime);
    }

    private static void addExecution(String algorithmName, long executionTime) {
        ExecutionStats stats = executionStatsMap.getOrDefault(algorithmName, new ExecutionStats());
        stats.incrementCount();
        stats.addTime(executionTime);
        executionStatsMap.put(algorithmName, stats);
        totalExecutionTime += executionTime;
    }

    static { // this called a static initializer block
        Runtime.getRuntime().addShutdownHook(new Thread(Logging::printSummary));
    }

    public static void printSummary() {
        System.out.println("Total time of running all sort functions was " + totalExecutionTime + " ms \nIn detail:");
        executionStatsMap.forEach((name, stats) -> System.out.println("Function sort in " + name + " ran " + stats.getCount() + " times and took in total " + stats.getTime() + " ms"));
    }

    private static class ExecutionStats {
        private int count;
        private long time;

        void incrementCount() {
            count++;
        }

        void addTime(long time) {
            this.time += time;
        }

        int getCount() {
            return count;
        }

        long getTime() {
            return time;
        }
    }
}