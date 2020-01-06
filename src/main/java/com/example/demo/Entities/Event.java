package com.example.demo.Entities;

import javax.persistence.*;

public class Event {

    private String id;

    private String state;

    private String timestamp;

    private String type;

    private String host;

    public Event() {
    }

    public Event(String id, String state, String timestamp, String type, String host) {
        this.id = id;
        this.state = state;
        this.timestamp = timestamp;
        this.type = type;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", state='" + state + '\'' +
                ", timeStamp='" + timestamp + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
