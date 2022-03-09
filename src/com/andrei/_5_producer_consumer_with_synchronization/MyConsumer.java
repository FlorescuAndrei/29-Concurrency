package com.andrei._5_producer_consumer_with_synchronization;

import java.util.List;

public class MyConsumer implements Runnable{
    private List<String> buffer;
    private String color;

    public MyConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    public void run() {
        while(true){

            synchronized (buffer) {
                if (buffer.isEmpty()) {
                    //if is nothing to read we loop again and keep checking
                    continue;
                }
                if(buffer.get(0).equals("EOF")){
                    System.out.println(color + "Exiting");
                    //break out of the loop. Exiting without removing EOF
                    break;
                } else{
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            }


        }
    }
}
