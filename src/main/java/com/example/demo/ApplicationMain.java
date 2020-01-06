package com.example.demo;

import com.example.demo.Service.EventHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationMain {


    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(ApplicationMain.class, args);
        EventHandler eventHandler = context.getBean(EventHandler.class);
        Thread t1 = new Thread(()->eventHandler.readEventsFromFile());
        t1.start();
        //Sleep for 100 millis so as to process all the log items.
        Thread.sleep(5);
        eventHandler.flagDelayedEventsAndWriteToDB();
        //Waits until t1 is completed so that the remaining map items can be processed to DB
        t1.join();
        eventHandler.flagDelayedEventsAndWriteToDB();

    }

}