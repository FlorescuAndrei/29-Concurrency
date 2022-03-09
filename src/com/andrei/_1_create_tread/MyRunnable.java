package com.andrei._1_create_tread;

import static com.andrei.ThreadColor.ANSI_GREEN;

public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(ANSI_GREEN + "Hello from MyRunnable implementation of run()");
    }
}
