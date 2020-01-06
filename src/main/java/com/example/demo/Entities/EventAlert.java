package com.example.demo.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Entity
(name = "EventAlert")
public class EventAlert {

    @Id
    @Column
    private String id;
    @Column
    private String type;
    @Column
    private String host;
    @Column
    private String startTime;
    @Column
    private String endTime;
    @Column
    private Integer duration;
    @Column
    private boolean alert;

    public EventAlert(){

    }
    public EventAlert(String id, String type, String host) {
        this.id = id;
        this.type = type;
        this.host = host;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }

    @Override
    public String toString() {
        return "EventAlert{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", host='" + host + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", duration=" + duration +
                ", alert=" + alert +
                '}';
    }
}
