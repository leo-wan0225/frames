package leo.wan.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 这个类不用了，改用spring aop的方式实现
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class CreateAndUpdateTimeInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(CreateAndUpdateTimeInterceptor.class);
    //默认的实体类新增字段名，约定大于配置
    private static final String CREATE_TIME = "createTime";
    //默认的实体类新增字段名
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Invocation invocation2 = invocation;
        try {
            //第一个参数为MappedStatement， Executor的update方法的内置参数
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            //第二个参数才是我们需要的处理的数据库Entity对象，比如通过updateByExample(parameter)中的参数
            Object parameter = invocation.getArgs()[1];
            if (parameter instanceof Map){
                parameter = ((Map) parameter).get("record")==null?parameter:((Map) parameter).get("record");
            }
            //得到sql类型
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            //新增
            Date value = new Date();
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                setFieldValue(parameter, CREATE_TIME,value);
                setFieldValue(parameter,UPDATE_TIME,value);
                //更新
            } else if (SqlCommandType.UPDATE.equals(sqlCommandType)) {
                setFieldValue(parameter,UPDATE_TIME,value);
            }
        } catch (Exception e) {
            logger.error("CreateAndUpdateTimeInterceptor error", e);
            throw new RuntimeException("CreateAndUpdateTimeInterceptor error");
        }
        return invocation.proceed();
    }

    //设置指定对象的值
    private void setFieldValue(Object parameter, String fieldName,Date value) {
        if (hashField(parameter, fieldName)) {
            try {
                Field field = parameter.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(parameter, value);
            } catch (Exception e) {
                logger.error("从[{}]中获取[{}]字段发生异常", parameter.getClass().getName(), fieldName,e);
                throw new  RuntimeException(e);
            }
        }else {
            logger.warn("[{}]中不包含[{}]字段，默认不设置该字段的值", parameter.getClass().getName(), fieldName);
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

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
