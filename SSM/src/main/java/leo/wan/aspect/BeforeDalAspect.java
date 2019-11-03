package leo.wan.aspect;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 用于实现系统自动维护创建时间和修改时间
 * 如有其它需要系统维护的字段可以扩展该类
 */
//order 的值越小，越优先执行

@Slf4j
@Aspect
@Component
public class BeforeDalAspect {
    //新增方法
    private static final String INSERT_METHOD_NAMES = "insertSelective,insertBatch";
    //更新方法
    private static final String UPDATE_METHOD_NAMES = "updateByPrimaryKeySelective";
    //默认的实体类新增字段名，约定大于配置
    private static final String CREATE_TIME = "createTime";
    //默认的实体类新增字段名
    private static final String UPDATE_TIME = "updateTime";
    @Before(value = "execution(* leo.wan.dao.*.*(..))")
    public void autoSetTime(JoinPoint joinPoint) {
        //获取被拦截的方法名
        String methodName = joinPoint.getSignature().getName();
        //获取方法的参数
        Object[] params = joinPoint.getArgs();
        //默认参数的第一个为实体类对象
        if (params == null || params.length < 1 || params[0] == null) {
            return;
        }
        Date value = new Date();
        //新增
        if (INSERT_METHOD_NAMES.contains(methodName)) {
            setObjectValue(params[0], CREATE_TIME,value);
            setObjectValue(params[0],UPDATE_TIME,value);
            //更新
        } else if (UPDATE_METHOD_NAMES.contains(methodName)) {
            setObjectValue(params[0],UPDATE_TIME,value);
        }
    }

    private void setObjectValue(Object params, String fieldName, Date value) {
        if (params instanceof List) {
            List<Object> objects = (List<Object>) params;
            for (Object object: objects) {
                setFieldValue(object,fieldName,value);
            }
        } else {
            setFieldValue(params,fieldName,value);
        }
    }

    //设置指定对象的值
    private void setFieldValue(Object parameter, String fieldName, Date value) {
        if (hashField(parameter, fieldName)) {
            try {
                Field field = parameter.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(parameter, value);
            } catch (Exception e) {
                log.error("从[{}]中获取[{}]字段发生异常", parameter.getClass().getName(), fieldName,e);
                throw new  RuntimeException(e);
            }
        }else {
            log.warn("[{}]中不包含[{}]字段，默认不设置该字段的值", parameter.getClass().getName(), fieldName);
        }
    }

    private boolean hashField(Object parameter, String fieldName) {
        Field[] fields = parameter.getClass().getDeclaredFields();
        boolean result = false;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
