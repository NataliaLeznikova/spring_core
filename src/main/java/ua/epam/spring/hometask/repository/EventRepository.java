package ua.epam.spring.hometask.repository;

import ua.epam.spring.hometask.domain.Event;

import java.util.HashMap;
import java.util.Map;

public abstract class EventRepository {

    public static Map<Long, Event> eventsMap = new HashMap<>();
}
