package print;

import java.sql.Connection;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于ReentrantLock
 */
public class Print3 {
    private static volatile int curState=1;
    private static ReentrantLock lock = new ReentrantLock();
    //一个condition对应一个等待队列，一个lock可以绑定多个condition，即支持多个等待队列。
    //相比synchronized，有多个等待队列，溶质下一个队列是也有针对性，即 竞争效率比较高
    // 效率最高的还是semaphore
    private static Condition conditionA  =lock.newCondition();
    private static Condition conditionB  =lock.newCondition();
    private static Condition conditionC  =lock.newCondition();

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        int cnt =1;

        pool.execute(new Worker("A",1,2,cnt,lock,conditionA,conditionB));
        pool.execute(new Worker("B",2,3,cnt,lock,conditionB,conditionC));
        pool.execute(new Worker("C",3,1,cnt,lock,conditionC,conditionA));
        pool.shutdown();
    }

    public static class Worker implements Runnable{
        private String key;
        private int targetState;
        private int nextState;
        private Integer count;
        private Lock lock;
        private Condition current;
        private Condition next;

        public Worker(String key, int targetState, int nextState, Integer count, Lock lock, Condition current, Condition next) {
            this.key = key;
            this.targetState = targetState;
            this.nextState = nextState;
            this.count = count;
            this.lock = lock;
            this.current = current;
            this.next = next;
        }


        @Override
        public void run() {
            this.lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (curState!=targetState){
                        current.await();
                    }
                    System.out.print(key);
                    curState=nextState;
                    //通知下一个等待队列
                    next.signal();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
