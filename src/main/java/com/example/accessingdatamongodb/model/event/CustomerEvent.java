package com.example.accessingdatamongodb.model.event;

import com.example.accessingdatamongodb.model.Customer;

public class CustomerEvent extends AbstractEvent<Customer> {

    private Customer customer;

    public CustomerEvent() {
    }

    @Override
    public void setData(Customer data) {
        this.customer = data;
    }
}
