package leo.wan.test.exception;

import java.sql.SQLException;

public class MyException extends  Exception{

    public MyException(String s, SQLException e) {
        super(s,e);
    }
}
