package com.andrei._7_producer_consumer_tryLock;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;

    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run() {

        //we use random to simulate real word application unpredicted time for threads.
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for(String num: nums) {
            try {
                System.out.println(color + "Adding ...." + num);

                // instead of synchronized we use bufferLock

                //acquire the look
                bufferLock.lock();

                //we use try finally block to make sure the look will be unlocked
                try{
                    buffer.add(num);
                }finally {

                    //release the lock - important. when we use synchronized the lock is release automatically but with Reentrant Look we have to release it.
                    bufferLock.unlock();

                }

                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){
                System.out.println("Producer was interrupted");
            }
        }
        System.out.println(color + "Adding EOF and exiting");

        bufferLock.lock();
        try {
            buffer.add("EOF");
        }finally {
            bufferLock.unlock();
        }

    }
}
