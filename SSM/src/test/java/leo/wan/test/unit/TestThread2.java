package leo.wan.test.unit;

import org.apache.poi.util.SystemOutLogger;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * thread 默认为前台线程。主线程执行完了，还会执行。且主线程不会等待子线程
 */
public class TestThread2 {
    @Test
    public void test1() throws InterruptedException {
        SysService sysService = new SysService();
        String[] strArry = new String[100];
        //final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            strArry[i] = i + "";
        }
        String error = "";
        StringBuffer stringBuffer = new
                StringBuffer();
        SysService sysService1 = new SysService();
        for (String temp : strArry) {
            Mythread mythread = new Mythread(sysService, temp, stringBuffer/*, countDownLatch*/);

            try {
                mythread.start();
            } catch (Exception e) {
                System.out.println("外层铺货到异常" + e);
            }

        }
        System.out.println("for 循环不会阻塞");
      //  countDownLatch.await();
        System.out.println(stringBuffer.toString());
    }


}
