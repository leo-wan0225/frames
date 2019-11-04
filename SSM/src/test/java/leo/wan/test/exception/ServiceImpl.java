package leo.wan.test.exception;

import java.sql.SQLException;

public class ServiceImpl implements  IService{
    @Override
    public void foo() throws Exception {

        try {
            throw new SQLException("I test throw an checked Exception");
        } catch (SQLException e) {
            throw new  MyException("包装一层同样的看看效果，而且该异常类的构造方法要调用父类的构造方法，将信息保存起来",e);
        }
    }
}
