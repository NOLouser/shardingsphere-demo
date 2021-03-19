package com.nolouser.demo.note.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印
 *
 */
public class AlternateExecution implements Runnable{

    public static Lock lock = new ReentrantLock();

    private Condition local;

    private Condition other;

    public AlternateExecution(Condition local, Condition other) {
        this.local = local;
        this.other = other;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try{
                lock.lock();

                // 唤醒对方线程
                other.signal();

                System.out.println(Thread.currentThread().getName() + " " + i);

                // 本地线程等待
                // 最后一次不等待，避免循环结束，不唤醒线程
                if (i < 9) {
                    local.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        Condition conditionA = AlternateExecution.lock.newCondition();
        Condition conditionB = AlternateExecution.lock.newCondition();

        Thread threadA = new Thread(new AlternateExecution(conditionA, conditionB));
        Thread threadB = new Thread(new AlternateExecution(conditionB, conditionA));

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadA.start();
        threadB.start();
    }
}
