package leo.wan.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.sound.midi.Soundbank;

/**
 * 拦截多个方法。||和or都可以。&&和and不起作用
 */
@Slf4j
@Aspect
@Component
public class MultipleAspect {
    @Before(value = "execution(* leo.wan.dao.QuestionItemMapperExt.updateByPrimaryKeySelective(..)) || " +
            "execution(* leo.wan.dao.QuestionItemMapperExt.getQuestionItems(..)) || " +
            "execution(* leo.wan.dao.QuestionItemMapperExt.getQuestionItemsByPage(..))")
    public void myBefore() {
        System.out.println("调用到了");
        System.out.println("testweohahhhhhhhwerwihrqhqhrofopwiwiwfwlniwefljefjjfweaj");
    }
}
