package com.andrei._3_variables_and_synchronization_counter.synchronizedBlock;

import com.andrei.ThreadColor;

public class MainCounterSynchronized {

    public static void main(String[] args) {

        Countdown countdown = new Countdown();

        // t1 and t2 share the same countdown object, we can synchronize on it

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");

        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

        // t1 and t2 share the same countdown object


    }

}

class Countdown {
//Instance variable i
    private int i;

    public void doCountdown(){
        String color;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                color = ThreadColor.ANSI_BLUE;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_RED;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        //do not use local variable to synchronize like color
        //we synchronize using the countdown object that the threads share
        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
            }
        }
    }
}

class CountdownThread extends Thread{
    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown){
        threadCountdown = countdown;
    }

    public void run(){
        threadCountdown.doCountdown();
    }
}
