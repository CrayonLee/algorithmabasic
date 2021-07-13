package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC1 {
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();

    volatile int value = 0;
    private int count;

    public PrintABC1(int count) {
        this.count = count;
    }

    public void print() {
        start(conditionA,conditionB,0,"A");
        start(conditionB,conditionC,1,"B");
        start(conditionC,conditionA,2,"C");
    }

    private void start(Condition condition1,Condition condition2,int val,String msg) {
        List list = new ArrayList<>();
        Map<Integer,Integer> map = new HashMap<>();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < count; i++) {
                    if(value%3!=val){
                        condition1.await();
                    }
                    System.out.println(msg);
                    condition2.signal();
                    value++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }).start();
    }

    public static void main(String[] args) {
        PrintABC1 printABC1 = new PrintABC1(3);
        printABC1.print();
    }
}
