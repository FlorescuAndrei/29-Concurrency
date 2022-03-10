package com.andrei._5_producer_consumer_reentrant_lock._a_producer_consumer_with_synchronization;

import java.util.List;
import java.util.Random;

public class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;

    public MyProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {

        //we use random to simulate real word application unpredicted time for threads.
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for(String num: nums) {
            try {
                System.out.println(color + "Adding ...." + num);

                synchronized (buffer) {
                    buffer.add(num);
                }

                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){
                System.out.println("Producer was interrupted");
            }
        }
        System.out.println(color + "Adding EOF and exiting");

        synchronized (buffer) {
            buffer.add("EOF");
        }
    }
}
