package com.example.demo;

import com.example.demo.Entities.EventAlert;
import com.example.demo.Repository.EventRepository;
import com.example.demo.Service.EventReader;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventReaderTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    //{"id":"12138", "state":"FINISHED", "type":"adfd","host":"23434","timestamp":"128"}
    public void whenSavingEvent_thenCorrect() {
        eventRepository.save(new EventAlert("12138", "adfd", "23434"));
        EventAlert eventAlert = eventRepository.findById("").orElseGet(()
                -> new EventAlert("12139", "someType", "someHost"));
        assertEquals(eventAlert.getId(), "12138");
    }

    @Test
    public void readLogFile() {
        Result result = JUnitCore.runClasses(EventReader.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }


}
