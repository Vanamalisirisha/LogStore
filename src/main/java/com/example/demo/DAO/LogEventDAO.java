package com.example.demo.DAO;

import com.example.demo.Entities.EventAlert;
import com.example.demo.Repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogEventDAO {
    private final EventRepository eventRepository;
    Logger logger = LoggerFactory.getLogger(LogEventDAO.class);

    public LogEventDAO(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addEventsToDB(EventAlert eventAlert){
        logger.info("Saving event with ID" +eventAlert.getId()+" to the DB");
        eventRepository.save(eventAlert);

    }
}
