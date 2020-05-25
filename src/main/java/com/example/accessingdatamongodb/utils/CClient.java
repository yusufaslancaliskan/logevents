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

    public void saveEvent(T data, String eventType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clss = data.getEventClass();
        AbstractEvent<T> abstractEvent = gettAbstractEvent(data, eventType, clss);
        AbstractRepository abstractRepository = (AbstractRepository) applicationContext.getBean(abstractEvent.getEventRepositoryClass());
        abstractRepository.save(abstractEvent);
    }

    private AbstractEvent<T> gettAbstractEvent(T data, String eventType, Class ec) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor eventConstructor = ec.getConstructor();
        AbstractEvent<T> abstractEvent = (AbstractEvent<T>) eventConstructor.newInstance();
        abstractEvent.setData(data);
        abstractEvent.setEventType(eventType);
        abstractEvent.setUserName("Yusuf"); //İşlemi yapan kullanıcıya istinaden tutulacak bilgi, sessiondan alınabilir.Şimdilik default bir değer.
        abstractEvent.setDate(new Date());
        return abstractEvent;
    }

    public void saveEventList(List<T> eventList, String eventType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (T event : eventList) {
            saveEvent(event, eventType);
        }
    }

}
