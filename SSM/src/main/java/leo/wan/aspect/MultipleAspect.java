package leo.wan.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;
import java.lang.annotation.Annotation;

/**
 * 拦截多个方法。||和or都可以。&&和and不起作用
 */
@Order(Integer.MAX_VALUE-1)//默认为Integer.MAX_VALUE,事务aop也是
@Slf4j
@Aspect
@Component
public class MultipleAspect {
    @Pointcut("execution(* leo.wan.dao.QuestionItemMapperExt.updateByPrimaryKeySelective(..)) || " +
            "execution(* leo.wan.dao.QuestionItemMapperExt.getQuestionItems(..)) || " +
            "execution(* leo.wan.dao.QuestionItemMapperExt.getQuestionItemsByPage(..))")
    public void pointcut1() {
    }

    @Pointcut("execution(* leo.wan.service.QuestionItemService.* (..))")
    public void pointcut2() {
    }

    /* @Before(value = "execution(* leo.wan.dao.QuestionItemMapperExt.updateByPrimaryKeySelective(..)) || "+
             "execution(* leo.wan.dao.QuestionItemMapperExt.getQuestionItems(..)) || " +
             "execution(* leo.wan.dao.QuestionItemMapperExt.getQuestionItemsByPage(..))")*/
    @Before("pointcut2()")
    public void myBefore() {
        System.out.println("调用到了");
        System.out.println("实践出真理");
        System.out.println("测试git");
        System.out.println("不要 stash 内容的提交");
    }

    @Around("pointcut2()")
    public Object myAround(ProceedingJoinPoint jp) throws Throwable {
        Object object = null;
        System.out.println("调用到了");
        try {
            object = jp.proceed(jp.getArgs());
        } catch (Throwable e) {
            throw e;
        }
        System.out.println("不要 stash 内容的提交");
        return object;
    }

    @After("pointcut2()")
    public void myAfter() {
        System.out.println("调用到了");
        System.out.println("实践出真理");
        System.out.println("测试git");
        System.out.println("不要 stash 内容的提交");
    }
}
