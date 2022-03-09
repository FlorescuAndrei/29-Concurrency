package com.andrei._1_create_tread;

import static com.andrei.ThreadColor.ANSI_GREEN;
import static com.andrei.ThreadColor.ANSI_RED;

public class MainCreateThreads {

    public static void main(String[] args) {

        System.out.println("Hello from the main thread");

    //Run a thread by creating a subclass of the Thread Class;
        Thread anotherThread = new MyThread();
        anotherThread.start();// can start only once.

        //if you need to start again create another instance
        Thread anotherThread2 = new MyThread();
        anotherThread2.start();

        System.out.println("Hello again from the main thread!");

    // Run a thread by creating an anonymous thread class if you run the code once.

        new Thread(){
            public void run(){
                System.out.println(ANSI_RED + "Hello from the anonymous class thread");
            }
        }.start();

    //Run the thread by creating a class that implement Runnable Interface
        Thread myRunnableThread = new Thread(new MyRunnable());
        myRunnableThread.start();

   // Run a thread by creating an anonymous runnable class
        Thread myRunnableThread2 = new Thread((new Runnable() {
            @Override
            public void run() {
                System.out.println(ANSI_GREEN + "Hello from the anonymous class implementation of Runnable");
            }
        }));
        myRunnableThread2.start();



//output order my vary. We can not assume that thread will run in order
    }
}
