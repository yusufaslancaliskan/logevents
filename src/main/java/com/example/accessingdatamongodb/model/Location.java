package com.example.accessingdatamongodb.model;

import com.example.accessingdatamongodb.model.event.AbstractEvent;
import com.example.accessingdatamongodb.repository.event.AbstractRepository;
import com.example.accessingdatamongodb.utils.EventClass;
import org.springframework.data.annotation.Id;


public class Location implements EventClass {

    @Id
    public String id;

    private int x;
    private int y;

    public Location() {
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Class<AbstractEvent> getEventClass() {
        try {
            return (Class<AbstractEvent>) Class.forName("com.example.accessingdatamongodb.model.event.LocationEvent");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

