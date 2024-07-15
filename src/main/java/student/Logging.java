package student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Logging {

    @Pointcut("execution(* *.*.set*(..))")
    private void selectSetters() {
    }

    @Pointcut("@annotation(Loggable) && execution(* *(..))")
    private void selectLoggable() {
    }

    @Before("selectSetters()")
    public void beforeSetters(JoinPoint jp) {
        System.out.println("Calling " + jp.getSignature().getName()
            + " with " + jp.getArgs()[0]);
    }

    @Before("selectLoggable()")
    public void beforeLoggable(JoinPoint jp) {
        System.out.println("Calling loggable function " + jp.getSignature().getName() + " from " + jp.getThis());
    }
}
