package test;

import sun.applet.Main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC{
    ReentrantLock lock = new ReentrantLock();
    Condition conditionA =lock.newCondition();
    Condition conditionB =lock.newCondition();
    Condition conditionC =lock.newCondition();

    volatile int value=0;
    private int count;
    public PrintABC(int count){
        this.count=count;
    }
    public void print(){
        new Thread(()->{
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    if(value%3!=0) {
                        conditionA.await();
                    }
                    System.out.println("A");
                    conditionB.signal();
                    value++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();



        new Thread(()->{
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    if(value%3!=1) {
                        conditionB.await();
                    }
                    System.out.println("B");
                    conditionC.signal();
                    value++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    if(value%3!=2) {
                        conditionC.await();
                    }
                    System.out.println("C");
                    conditionA.signal();
                    value++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();
    }
    public static void main(String[] args) {
        PrintABC printABC = new PrintABC(2);
        printABC.print();
    }
}
