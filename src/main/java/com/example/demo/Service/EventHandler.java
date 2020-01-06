package com.example.demo.Service;
import com.example.demo.DAO.LogEventDAO;
import com.example.demo.Entities.Event;
import com.example.demo.Entities.EventAlert;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class EventHandler {
    Logger logger = LoggerFactory.getLogger(EventHandler.class);

    private final EventReader eventReader;
    private final LogEventDAO logEventDAO;
    ConcurrentHashMap<String, EventAlert> hm = new ConcurrentHashMap<>();

    @Autowired
    public EventHandler(EventReader eventReader, LogEventDAO logEventDAO) {
        this.eventReader = eventReader;
        this.logEventDAO = logEventDAO;
    }

    //Reads the textFile and places the JSON objects into a
    public void readEventsFromFile() {
        try {
            eventReader.readLogFile(hm);
        } catch (IOException e) {
            logger.error("I/O exception occurred while reading the log File",e.getMessage());
        }
    }
    //Reads the concurrent hashMap and inserts eventAlerts into DB while flagging the events which take longer than 4ms.
    public void flagDelayedEventsAndWriteToDB(){
        for(String key: hm.keySet()) {
            EventAlert eventAlert = hm.get(key);
            if (StringUtils.isNotEmpty(eventAlert.getStartTime()) && StringUtils.isNotEmpty(eventAlert.getEndTime())) {
                Integer endTime = Integer.parseInt(eventAlert.getEndTime());
                Integer startTime = Integer.parseInt(eventAlert.getStartTime());
                eventAlert.setDuration(endTime - startTime);
                if (eventAlert.getDuration() > 4) {
                    eventAlert.setAlert(true);
                }
                logEventDAO.addEventsToDB(eventAlert);
                hm.remove(key);
            }
        }
    }

}
