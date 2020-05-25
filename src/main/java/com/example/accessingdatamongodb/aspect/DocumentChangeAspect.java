package com.example.accessingdatamongodb.aspect;

import com.example.accessingdatamongodb.utils.CClient;
import com.example.accessingdatamongodb.utils.EventClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class DocumentChangeAspect {
    private static final String EVENT_DOCUMENT_ADDED = "DOCUMENT_ADDED";
    private static final String EVENT_DOCUMENT_DELETED = "DOCUMENT_DELETED";

    @Autowired
    private CClient cClient;

    @Before("execution(* com.example.accessingdatamongodb.repository.*.delete(Object))")
    public void beforeDeleteObject(JoinPoint joinPoint) {
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            EventClass eventClass = (EventClass) joinPoint.getArgs()[i];
            saveEvent(eventClass, DocumentChangeAspect.EVENT_DOCUMENT_DELETED);
        }
    }

    @Before("execution(* com.example.accessingdatamongodb.repository.*.deleteAll(Iterable))")
    public void beforeDeleteIteratable(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        List<EventClass> eventClassList = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            eventClassList = (List<EventClass>) objects[i];
        }

        if (eventClassList.size() > 0) {
            saveEvents(eventClassList, DocumentChangeAspect.EVENT_DOCUMENT_DELETED);
        }
    }


    @After("execution(* com.example.accessingdatamongodb.repository.*.save(Object))")
    public void afterSave(JoinPoint joinPoint) {
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            EventClass eventClass = (EventClass) joinPoint.getArgs()[i];
            saveEvent(eventClass, DocumentChangeAspect.EVENT_DOCUMENT_ADDED);
        }
    }

    @After("execution(* com.example.accessingdatamongodb.repository.*.saveAll(Iterable))")
    public void afterSaveIterable(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        List<EventClass> eventClassList = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            eventClassList = (List<EventClass>) objects[i];
        }

        if (eventClassList.size() > 0) {
            saveEvents(eventClassList, DocumentChangeAspect.EVENT_DOCUMENT_ADDED);
        }
    }

    private void saveEvents(List<EventClass> events, String eventType) {
        try {
            cClient.saveEventList(events, eventType);
        } catch (NoSuchMethodException e) {
            new NoSuchMethodException(e.getMessage());
        } catch (IllegalAccessException e) {
            new IllegalAccessException(e.getMessage());
        } catch (InvocationTargetException e) {
            new InvocationTargetException(e);
        } catch (InstantiationException e) {
            new InstantiationException(e.getMessage());
        }
    }


    private void saveEvent(EventClass eventClass, String eventType) {
        try {
            cClient.saveEvent(eventClass, eventType);
        } catch (NoSuchMethodException e) {
            new NoSuchMethodException(e.getMessage());
        } catch (IllegalAccessException e) {
            new IllegalAccessException(e.getMessage());
        } catch (InvocationTargetException e) {
            new InvocationTargetException(e);
        } catch (InstantiationException e) {
            new InstantiationException(e.getMessage());
        }
    }

}
