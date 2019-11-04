package leo.wan.test.exception;

import java.sql.SQLException;

public class MyException extends  Exception{

    public MyException(String s, Exception e) {
        super(s,e);
    }

    public MyException(String s) {
        super(s);
    }
}
