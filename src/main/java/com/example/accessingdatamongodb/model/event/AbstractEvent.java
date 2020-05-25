package com.example.accessingdatamongodb.model.event;

import com.example.accessingdatamongodb.repository.event.AbstractRepository;
import org.springframework.data.annotation.Id;

import java.util.Date;

public abstract class AbstractEvent<T> {

    @Id
    private String id;

    private String userName;
    private Date date;
    private String eventType;

    public abstract void setData(T data);

    public abstract Class<AbstractRepository> getEventRepositoryClass();

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
