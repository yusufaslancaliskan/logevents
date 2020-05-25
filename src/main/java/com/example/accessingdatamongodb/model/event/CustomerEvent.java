package com.example.accessingdatamongodb.model.event;

import com.example.accessingdatamongodb.model.Customer;
import com.example.accessingdatamongodb.repository.event.AbstractRepository;

public class CustomerEvent extends AbstractEvent<Customer> {

    private Customer customer;

    public CustomerEvent() {
    }

    @Override
    public void setData(Customer data) {
        this.customer = data;
    }


    public Class<AbstractRepository> getEventRepositoryClass() {
        try {
            return (Class<AbstractRepository>) Class.forName("com.example.accessingdatamongodb.repository.event.CustomerEventRepository");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
