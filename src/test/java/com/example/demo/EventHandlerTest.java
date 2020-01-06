package com.example.demo;

import com.example.demo.Service.EventHandler;
import com.example.demo.Service.EventReader;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class EventHandlerTest {
@Test
public void EventAlertTest() {
    Result result = JUnitCore.runClasses(EventHandler.class);

    for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
    }
    System.out.println(result.wasSuccessful());
}
}
