package com.chl.bookmanager.aop;

import com.chl.bookmanager.exception.BizException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author:Administrator
 * @date:2018/12/24/024
 */
@Component
@Aspect
public class ServiceAop {
    //定义切入点
    public static final String POINT_CUT = "execution(* com.chl.bookmanager.service..*.*(..))";
//    前置
    @Before(POINT_CUT)
    public void before(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature().getName()+"===========事务开始");
    }
    //后置最终通知
    @After(POINT_CUT)
    public void afterFinallyAdvice(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName()+"===========事务结束");
    }
    //抛出异常后
    @AfterThrowing(value = POINT_CUT,throwing = "ex")
    public void afterThrowsAdvice(JoinPoint joinPoint,Exception ex) {
        System.out.println(ex.getMessage());
        System.out.println(joinPoint.getSignature().getName()+"===========事务异常");
    }
    @Around(value = POINT_CUT)
    public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable{
        //System.out.println("环绕通知,方法执行之前..");
        long start = System.currentTimeMillis();
        Object obj = jp.proceed();//调用业务方法
        long end = System.currentTimeMillis();

        System.out.println("===消耗时间:"+(end-start));
        //System.out.println("环绕通知,方法执行之后..");

        return obj;
    }
}
