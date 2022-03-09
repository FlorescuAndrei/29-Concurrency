package com.andrei._1_create_tread;

import static com.andrei.ThreadColor.ANSI_BLUE;

public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println(ANSI_BLUE + "Hello from another thread! Thread name ");
    }
}
