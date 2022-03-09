package com.andrei._6_producer_consumer_with_lock;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MyConsumer implements Runnable{
    private List<String> buffer;
    private String color;

    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    public void run() {
        while(true){

            //instead of synchronized block we use a lock
            // we surround the code with try finally to make sure the lock will be unlocked
            bufferLock.lock();

            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                if(buffer.get(0).equals("EOF")){
                    System.out.println(color + "Exiting");
                    break;
                } else{
                    System.out.println(color + "Removed " + buffer.remove(0));
                }

            }finally {
                bufferLock.unlock();
            }



        }
    }
}
