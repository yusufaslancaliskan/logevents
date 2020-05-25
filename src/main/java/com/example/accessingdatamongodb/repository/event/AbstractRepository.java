package com.example.accessingdatamongodb.repository.event;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T, S> extends MongoRepository<T,S> {
}
