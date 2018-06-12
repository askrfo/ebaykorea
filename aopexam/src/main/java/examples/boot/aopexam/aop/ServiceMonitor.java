package examples.boot.aopexam.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {
    @AfterReturning("execution(* examples..*Service.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        System.out.println("name : " + joinPoint.getSignature().getName());
        System.out.println("Completed: " + joinPoint);
    }

    @AfterThrowing(value = "execution(* examples..*Service.*(..))", throwing = "ex")
    public void afterException(JoinPoint joinPoint, Exception ex) {
        System.out.println("exception :" + joinPoint);
        System.out.println(ex.getMessage());
    }
}
