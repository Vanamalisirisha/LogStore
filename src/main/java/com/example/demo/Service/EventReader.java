package com.example.demo.Service;

import com.example.demo.Entities.Event;
import com.example.demo.Entities.EventAlert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventReader {
    Logger logger = LoggerFactory.getLogger(EventReader.class);
    //Reads the textFile using Scanner and Filters the Events based on State so that the Concurrent HashMap is
    // populated with required EventAlert objects.
    public void readLogFile(ConcurrentHashMap<String, EventAlert> hm ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream("EVENTS.txt");
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(StringUtils.isNotEmpty(line)) {
                    Event event = objectMapper.readValue(line, new TypeReference<Event>() {
                    });
                    filterEventsOnState(hm,event);
                }
            }
            if (sc.ioException() != null) { throw sc.ioException(); } }
        finally { if (inputStream != null) { inputStream.close(); } if (sc != null) { sc.close();} }
    }

    //Sets the EventAlert objects in ConcurrentHashMap based on the ID's
    public void filterEventsOnState(ConcurrentHashMap<String, EventAlert> hm,Event event) {
            if (!hm.containsKey(event.getId())) {
                EventAlert eventAlert = new EventAlert(event.getId(),event.getType(),event.getHost());
                setTimeFromEventState(event,eventAlert);
                hm.put(event.getId(), eventAlert);
            } else {
                EventAlert eventAlert = hm.get(event.getId());
                setTimeFromEventState(event, eventAlert);
            }
            logger.info("Added EventAlert"+ event.getId()+ " withEventState "+ event.getState());

    }

    public void setTimeFromEventState(Event event,EventAlert eventAlert){
        if(event.getState().equalsIgnoreCase("FINISHED")){
            eventAlert.setEndTime(event.getTimestamp());
        }else{
            eventAlert.setStartTime(event.getTimestamp());
        }
    }
}
