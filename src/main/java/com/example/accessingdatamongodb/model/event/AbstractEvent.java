package com.example.accessingdatamongodb.model.event;

import org.springframework.data.annotation.Id;

import java.util.Date;

public abstract class AbstractEvent<T> {

    @Id
    private String id;

    private int version;
    private Date date;

    public abstract void setData(T data);

    public void setVersion(int version) {
        this.version = version;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
