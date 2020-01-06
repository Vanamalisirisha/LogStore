package com.example.demo.Repository;

import com.example.demo.Entities.Event;
import com.example.demo.Entities.EventAlert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<EventAlert, String> {
}
