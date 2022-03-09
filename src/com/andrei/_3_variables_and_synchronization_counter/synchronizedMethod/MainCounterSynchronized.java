package com.andrei._3_variables_and_synchronization_counter.synchronizedMethod;

import com.andrei.ThreadColor;

public class MainCounterSynchronized {

    public static void main(String[] args) {

        Countdown countdown = new Countdown();

        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");

        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();


    }

}

class Countdown {
//Instance variable i
    private int i;

    //when synchronized thread 1 run the method and terminate, then thread 2 run the method
    //synchronized the method. Better to synchronize only the for loop to keep the code we synchronize to an absolute minimum.
    public synchronized void doCountdown(){
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
        for (i = 10; i > 0; i--){
            System.out.println(color + Thread.currentThread().getName() + ": i = " + i);
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
