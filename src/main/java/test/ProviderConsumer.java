package test;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProviderConsumer<T> {
    private int length ;
    private Queue<T> queue;
    private ReentrantLock lock = new ReentrantLock();
    private Condition providerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    public Queue<T> getQueue() {
        return queue;
    }

    public ProviderConsumer(int length) {
        this.length = length;
        this.queue=new LinkedList<>();
    }

    public void provide(T product){
        lock.lock();
        try {
            while (queue.size()>=length){
                providerCondition.await();;
            }
            queue.add(product);
            consumerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public T consume(){
        try {
            lock.lock();
            while (queue.isEmpty()){
                consumerCondition.await();
            }
            T product = queue.remove();
            providerCondition.signal();
            return product;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        ProviderConsumer<Integer> providerConsumer = new ProviderConsumer<>(10);
        providerConsumer.provide(1);
        providerConsumer.provide(2);
        providerConsumer.provide(3);
        while (!providerConsumer.getQueue().isEmpty()) {
            System.out.println(providerConsumer.consume());
        }
    }
}
