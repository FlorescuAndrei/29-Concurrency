package com.andrei._5_producer_consumer_with_synchronization;

import com.andrei.ThreadColor;

import java.util.ArrayList;
import java.util.List;

//use classic synchronization
public class ProducerConsumerMain {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        //ArrayList is not synchronized
        List<String> buffer = new ArrayList<String>();

        MyProducer producer = new MyProducer(buffer, ThreadColor.ANSI_RED);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColor.ANSI_BLUE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColor.ANSI_GREEN);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();




    }
}
