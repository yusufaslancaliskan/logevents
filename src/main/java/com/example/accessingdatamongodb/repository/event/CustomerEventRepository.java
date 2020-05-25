package com.example.accessingdatamongodb.repository.event;

import com.example.accessingdatamongodb.model.event.CustomerEvent;
import com.example.accessingdatamongodb.repository.event.AbstractRepository;

public interface CustomerEventRepository extends AbstractRepository<CustomerEvent,String> {

}
