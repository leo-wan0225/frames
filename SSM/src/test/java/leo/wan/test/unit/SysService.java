package leo.wan.test.unit;

import org.apache.poi.util.SystemOutLogger;

public class SysService {
    public void doSomething(String tem){
        System.out.println(Thread.currentThread().getName()+"--------"+tem);

    }
}
