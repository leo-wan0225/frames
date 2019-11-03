package leo.wan.aspect;

import leo.wan.common.SupplierAnswerService2;
import leo.wan.common.SupplierAnswerService3;
import leo.wan.dao.SupplierAnswerMapper;
import leo.wan.service.SupplierAnswerService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 拦截多个方法。||和or都可以。&&和and不起作用
 */
@Order(Integer.MAX_VALUE)//默认为Integer.MAX_VALUE,事务aop也是
@Slf4j
@Aspect
@Component
public class MultipleAspect2 {
    @Pointcut("execution(* leo.wan.dao.QuestionItemMapperExt.updateByPrimaryKeySelective(..)) || " +
            "execution(* leo.wan.dao.QuestionItemMapperExt.getQuestionItems(..)) || " +
            "execution(* leo.wan.dao.QuestionItemMapperExt.getQuestionItemsByPage(..))")
    public void pointcut1() {
    }

    @Pointcut("execution(* leo.wan.service.QuestionItemService.* (..))")
    public void pointcut2() {
    }
    @Autowired
    private SupplierAnswerMapper supplierAnswerMapper;
    @Autowired
    private SupplierAnswerService supplierAnswerService;
    @Autowired
    private SupplierAnswerService3 supplierAnswerService3;
    /*@Transactional(rollbackFor = Exception.class)*/

    @Before("pointcut2()")
    public void myBefore() {
        //这里用没有被事务管理的方法操作数据库,且在事务aop的前面执行，看是否是在一个事务里面
       /* SupplierAnswer supplierAnswer = new SupplierAnswer();
        supplierAnswer.setQuestionId("1");
        supplierAnswer.setSupplierAnswer("1");
        supplierAnswer.setSupplierName("ces");
        supplierAnswerMapper.insert(supplierAnswer);*/
        System.out.println("开始执行");
        supplierAnswerService.add();
        System.out.println("这个地方事务应该已经提交了啊");
        supplierAnswerService3.add();
        System.out.println("调用到了");
        System.out.println("实践出真理");
        System.out.println("测试git");
        System.out.println("不要 stash 内容的提交");
    }
    //这个地方不加事务处理，但是在事务aop之后执行的话确实也会和目标方法在同一个事务里面

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
