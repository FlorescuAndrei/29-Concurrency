package com.andrei._4_wait_notify_message;

import java.util.Random;

public class MessageWriter implements Runnable{
    private Message message;

    public MessageWriter(Message message) {
        this.message = message;
    }

    public void run() {
        String[] messages ={
                "Humpty Dumpty sit on a wall",
                "Humpty Dumpty had a great fall",
                "All the king's horses and all the king's man",
                "Couldn't put Humpty together again"
        };

        Random random = new Random();
        for(int i =0; i < messages.length; i++){
            message.write(messages[i]);
            try{
                Thread.sleep(random.nextInt(3000));
            }catch (InterruptedException e){

            }
        }
        message.write("Finished");
    }
}
