package com.andrei._2_sleep_join_interrupt;

import static com.andrei.ThreadColor.ANSI_BLUE;

public class ThreadOne extends Thread{

    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from ThreadOne! Thread name = " + currentThread().getName());

    //sleep
        try{
            Thread.sleep(5000);

        }catch (InterruptedException e){

    //interrupt() in main class. Main thread will interrupt
            System.out.println(ANSI_BLUE + "Main  thread woke me up: - threadOne.interrupt() in Main -");
            return;
        }

        System.out.println(ANSI_BLUE + "Three seconds have passed an I'm awake");
    }
}
