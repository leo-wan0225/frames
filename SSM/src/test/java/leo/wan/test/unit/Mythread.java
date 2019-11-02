package leo.wan.test.unit;

import javax.swing.*;
import java.util.concurrent.CountDownLatch;

public class Mythread extends Thread {
    private  SysService sysService;
    private  String string;
    private StringBuffer err;
    //private CountDownLatch countDownLatch;

    public Mythread(SysService sysService, String string, StringBuffer err/*, CountDownLatch countDownLatch*/) {
        this.sysService = sysService;
        this.string = string;
        this.err = err;
        //this.countDownLatch = countDownLatch;
    }

    @Override
    public void run()  {
        if (Long.valueOf(string)%5==0){
            err.append("异常"+string);
           // countDownLatch.countDown();
            throw  new  RuntimeException(string+"抛出了异常");
        }
        sysService.doSomething(string);
        //countDownLatch.countDown();
    }

}
