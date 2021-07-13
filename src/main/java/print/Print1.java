package print;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 多个线程并发顺序打印ABC
 * 基于Semaphore
 * 一个先后关系需要一个信号量来进行控制，n个先后关系就需要n个信号量。(见操作系统书中例子)这个例子是其有向环图版本。
 */
public class Print1 {
    public static void main(String[] args) {
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //打印次数
        Integer cnt = 1;
        pool.execute(new Worker(a,b,"A",cnt));
        pool.execute(new Worker(b,c,"B",cnt));
        pool.execute(new Worker(c,a,"C",cnt));
        pool.shutdown();
    }

    public static class Worker implements Runnable {
        private final Semaphore current;
        private final Semaphore next;
        private final String key;
        private final Integer count;

        public Worker(Semaphore current, Semaphore next, String key, Integer count) {
            this.current = current;
            this.next = next;
            this.key = key;
            this.count = count;
        }

        @Override
        public void run() {
            for(int i=0;i<count;i++){
                try {
                    //获取资源
                    current.acquire();
                    System.out.print(key);
                    //传递资源
                    next.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
