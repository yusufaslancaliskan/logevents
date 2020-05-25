package com.example.accessingdatamongodb.utils;

import com.example.accessingdatamongodb.model.event.AbstractEvent;
import com.example.accessingdatamongodb.repository.event.AbstractRepository;

public interface EventClass {

    Class<AbstractEvent> getEventClass();
}
