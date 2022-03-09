package com.andrei._2_sleep_join_interrupt;


import static com.andrei.ThreadColor.ANSI_RED;

public class MainSleepInterruptJoin {

    public static void main(String[] args) {

        System.out.println("Hello from the main thread");

    //name a thread;
        Thread threadOne = new ThreadOne();
        threadOne.setName("**ThreadOne**");
        threadOne.start();// can start only once.

        Thread myRunnableThreadTwo = new Thread((new Runnable() {
            @Override
            public void run() {
                System.out.println(ANSI_RED + "Hello from the anonymous class implementation of Runnable");
    //join - when we join thread2 with thread1, thread 2 will wait for thread1 to terminate, or specified time to pass and then will continue to execute
                try {
                    threadOne.join(2000);
                    System.out.println(ANSI_RED + "ThreadOne terminated or two second passed, so I'm running again");
                } catch (InterruptedException e) {
                    System.out.println(ANSI_RED + "I couldn't wait after all. I was interrupted");
                }
            }
        }));
        myRunnableThreadTwo.start();

    //interrupt()
        threadOne.interrupt();


    }
}
