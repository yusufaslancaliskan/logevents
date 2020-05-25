package com.example.accessingdatamongodb.model;

import com.example.accessingdatamongodb.model.event.AbstractEvent;
import com.example.accessingdatamongodb.repository.event.AbstractRepository;
import com.example.accessingdatamongodb.utils.EventClass;
import org.springframework.data.annotation.Id;


public class Customer implements EventClass {

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Class<AbstractEvent> getEventClass() {
        try {
            return (Class<AbstractEvent>) Class.forName("com.example.accessingdatamongodb.model.event.CustomerEvent");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}

