package com.example.accessingdatamongodb.repository;

import com.example.accessingdatamongodb.model.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {

}
