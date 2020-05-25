package com.example.accessingdatamongodb.aspect;

import com.example.accessingdatamongodb.model.event.AbstractEvent;
import com.example.accessingdatamongodb.utils.CClient;
import com.example.accessingdatamongodb.utils.EventClass;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class DocumentChangeAspect {

    private Logger logger = LoggerFactory.getLogger(DocumentChangeAspect.class);

    @Autowired
    private CClient cClient;


    @After("execution(* com.example.accessingdatamongodb.repository.*.*(Object))")
    public void afterSave(JoinPoint joinPoint) {
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            EventClass eventClass = (EventClass) joinPoint.getArgs()[i];
            saveEvent(eventClass);
        }


        logger.info("{}", joinPoint.getArgs());
        logger.info(joinPoint.getKind());
    }

    @After("execution(* com.example.accessingdatamongodb.repository.*.*(Iterable))")
    public void afterSaveIterable(JoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
        List<EventClass> eventClassList = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            eventClassList = (List<EventClass>) objects[i];
        }

        if (eventClassList.size() > 0) {
            saveEvents(eventClassList);
        }
    }

    private void saveEvents(List<EventClass> events) {
        try {
            cClient.saveEventList(events);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    private void saveEvent(EventClass eventClass) {
        try {
            cClient.saveEvent(eventClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

}
