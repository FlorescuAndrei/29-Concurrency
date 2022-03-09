package com.andrei._4_wait_notify_message;

//Producer-Consumer example show the use for methods that are allowed only in synchronized code: wait(), notify(), notifyAll()


public class MessageMain {
    public static void main(String[] args) {

        Message message = new Message();

        (new Thread(new MessageWriter(message))).start();

        (new Thread(new MessageReader(message))).start();


    }
}
