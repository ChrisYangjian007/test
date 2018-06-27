package Common;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017-09-21.
 */
public class CountDownEvent {
    private ManualResetEvent done;
    private int total = 0;
    // 定义原子变量
    private AtomicInteger number = null;

    public CountDownEvent(int total) {
        this.total = total;
        number = new AtomicInteger(total);
        done = new ManualResetEvent(false);
    }

    public void Signal() {
        if (number.getAndDecrement() == 0) {
            done.Set();
        }
    }

    public void Wait() throws InterruptedException {
        done.WaitOne();
    }

    public Boolean Wait(Long timeout) throws InterruptedException {
        return done.WaitOne(timeout);
    }
}
