package com.andrei._4_wait_notify_message;

import java.util.Random;

public class MessageReader implements  Runnable{

    private  Message message;

    public MessageReader(Message message) {
        this.message = message;
    }

    public void run(){
        Random random = new Random();

        for(String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage =  message.read()){

            System.out.println(latestMessage);

            try {
                Thread.sleep(random.nextInt(3000));
            }catch (InterruptedException e){

            }
        }
    }
}
