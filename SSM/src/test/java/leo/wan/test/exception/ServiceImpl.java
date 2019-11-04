package leo.wan.test.exception;

import java.sql.SQLException;

public class ServiceImpl implements  IService{
    @Override
    public void foo() throws Exception {

        try {
            throw new MyException("I test throw an checked Exception");
        } catch (MyException e) {
            try {
                throw new  SQLException("包装一层同样的看看效果，而且该异常类的构造方法要调用父类的构造方法，将信息保存起来",e);
            } catch (Exception ex) {
                try {
                    throw new  MyException("MyException",ex);
                } catch (MyException exc) {
                    throw new SQLException("not reall ",exc);
                }
            }
        }
    }
}
