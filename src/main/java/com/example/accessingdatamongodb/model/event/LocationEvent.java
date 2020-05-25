package com.example.accessingdatamongodb.model.event;

import com.example.accessingdatamongodb.model.Location;
import com.example.accessingdatamongodb.repository.event.AbstractRepository;

public class LocationEvent extends AbstractEvent<Location> {

    private Location location;

    public LocationEvent() {
    }

    @Override
    public void setData(Location data) {
        this.location = data;
    }

    public Class<AbstractRepository> getEventRepositoryClass() {
        try {
            return (Class<AbstractRepository>) Class.forName("com.example.accessingdatamongodb.repository.event.LocationEventRepository");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
