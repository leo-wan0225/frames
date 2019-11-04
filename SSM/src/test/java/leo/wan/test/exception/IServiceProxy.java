package leo.wan.test.exception;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class IServiceProxy implements InvocationHandler {
    private Object target;

    IServiceProxy(Object target) {
        this.target = target;
    }

    //什么是受检查异常，就是编译的时候要求你处理的异常
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            result = method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
           throw e.getCause();
        }
        return result;
    }

    public static void main(String[] args) {
        IService service = new ServiceImpl();
        IService serviceProxy = (IService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(), new IServiceProxy(service));
        try {
            serviceProxy.foo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
