package com.andrei._4_wait_notify_message;

public class Message {
    private String message;
    private boolean empty = true;// no message to read

    //will be read by the consumer to read the message
    public synchronized String read(){
        while(empty){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        empty = true;
        notifyAll();
        return message;
    }

    //will be used by the producer to write the message
    public synchronized void write(String message){
        while (!empty){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }
}

//we want to read each message before we write another one