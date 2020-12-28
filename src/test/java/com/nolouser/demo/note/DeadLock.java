package com.nolouser.demo.note;

/**
 * 简单的死锁示例
 */
public class DeadLock {

    private Object resource;

    public DeadLock(Object resource) {
        this.resource = resource;
    }

    public void getOtherResource(Object otherResource) {
        // 对持有资源上锁
        synchronized (resource) {
            System.out.println(Thread.currentThread().getName() + " has a resource:" + resource);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 在未释放持有资源的时候去获取其他的资源
            synchronized (otherResource) {
                System.out.println(Thread.currentThread().getName() + " get other resource:" + otherResource);
            }
        }
    }

    public static void main(String[] args) {
        Object resourceA = new Object();
        Object resourceB = new Object();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                new DeadLock(resourceA).getOtherResource(resourceB);

            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                new DeadLock(resourceB).getOtherResource(resourceA);
            }
        });

        // 主线程等待子线程执行完成
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
