package com.example.accessingdatamongodb.utils;

import com.example.accessingdatamongodb.model.event.AbstractEvent;
import com.example.accessingdatamongodb.repository.event.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Component
public class CClient<T extends EventClass> {

    @Autowired
    private ApplicationContext applicationContext;

    public void saveEvent(T customer) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clss = customer.getEventRepositoryClass();
        Class eventClass = customer.getEventClass();
        Constructor eventConstructor = eventClass.getConstructor();
        AbstractRepository abstractRepository = (AbstractRepository) applicationContext.getBean(clss);
        AbstractEvent<T> abstractEvent = (AbstractEvent<T>) eventConstructor.newInstance();
        abstractEvent.setData(customer);
        abstractEvent.setVersion(0);
        abstractEvent.setDate(new Date());
        abstractRepository.save(abstractEvent);
    }

    public void saveEventList(List<T> eventList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
       for (T event : eventList){
           saveEvent(event);
       }
    }

}
