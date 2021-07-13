package print;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 设计思想是, 用一个lock代表打印资格, 谁获取到谁可以打印;
// 并且用一个state来控制打印顺序, 一个线程打印完后, 将state变为下一个状态,
// 即,state在 a->b->c->a->b->...这样流转;
// 这个思想跟semaphore差不多的

public class Print2 {
    public static volatile int curState = 1;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        int cnt = 1;
        Object lock = new Object();
        pool.execute(new Worker("A", 1, 2, cnt, lock));
        pool.execute(new Worker("B", 2, 3, cnt, lock));
        pool.execute(new Worker("C", 3, 1, cnt, lock));

        Thread.sleep(1000);
        pool.shutdown();
    }

    public static class Worker implements Runnable {
        private String key;
        private int targetState;
        private int nextState;
        private int count;
        private Object lock;

        public Worker(String key, int targetState, int nextState, int count, Object lock) {
            this.key = key;
            this.targetState = targetState;
            this.nextState = nextState;
            this.count = count;
            this.lock = lock;
        }


        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                synchronized (lock) {
                    //判断当前应该谁打印
                    while (curState != targetState) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(key);
                    curState = nextState;
                    lock.notifyAll();
                }
            }
        }
    }
}
