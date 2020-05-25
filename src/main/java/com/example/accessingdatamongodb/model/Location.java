package com.example.accessingdatamongodb.model;

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

    public Class<AbstractRepository> getEventRepositoryClass() {
        try {
            return (Class<AbstractRepository>) Class.forName("com.example.accessingdatamongodb.repository.event.LocationEventRepository");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Class<AbstractRepository> getEventClass() {
        try {
            return (Class<AbstractRepository>) Class.forName("com.example.accessingdatamongodb.model.event.LocationEvent");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

