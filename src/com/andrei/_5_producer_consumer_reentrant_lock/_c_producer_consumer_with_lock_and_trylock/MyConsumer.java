package com.andrei._5_producer_consumer_reentrant_lock._c_producer_consumer_with_lock_and_trylock;

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

        int counter = 0;

        while(true){

            //check if the lock is available
            if(bufferLock.tryLock()) {

                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }

                    System.out.println(color + "The counter = " + counter);
                    counter = 0;
                    if (buffer.get(0).equals("EOF")) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }

                } finally {
                    bufferLock.unlock();
                }

            }else{
                counter++;
            }



        }
    }
}
