package com.example.accessingdatamongodb.utils;

import com.example.accessingdatamongodb.repository.event.AbstractRepository;

public interface EventClass {

    Class<AbstractRepository> getEventRepositoryClass();

    Class<AbstractRepository> getEventClass();
}
