package com.andrei._5_producer_consumer_reentrant_lock._c_producer_consumer_with_lock_and_trylock;

import com.andrei.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

// instead of classic synchronization we use an Object Lock an instance of ReentrantLock class from java.util.concurrent package
// we check if the lock is available in MyConsumer class. tryLock()
public class ProducerConsumerMain {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        //ArrayList is not synchronized
        List<String> buffer = new ArrayList<String>();

        ReentrantLock bufferLock = new ReentrantLock();

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_RED, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_BLUE, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();




    }
}
