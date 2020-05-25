package com.example.accessingdatamongodb.model.event;

import com.example.accessingdatamongodb.model.Location;

public class LocationEvent extends AbstractEvent<Location> {

    private Location location;

    public LocationEvent() {
    }

    @Override
    public void setData(Location data) {
        this.location = data;
    }
}
