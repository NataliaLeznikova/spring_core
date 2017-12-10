package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.NavigableSet;

import static ua.epam.spring.hometask.repository.EventRepository.eventsMap;

public class EventServiceImpl implements EventService{

    public EventServiceImpl() {
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        Event event = null;

        for (Map.Entry<Long, Event> entry : eventsMap.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                event = entry.getValue();
                break;
            }
        }
        return event;
    }

    @Override
    public Event save(@Nonnull Event object) {
        return eventsMap.put((long) (eventsMap.size() + 1), object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventsMap.values().remove(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventsMap.get(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return new ArrayList<>(eventsMap.values());
    }

    public Collection<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        ArrayList<Event> events = new ArrayList<>();

        for (Map.Entry<Long, Event> entry : eventsMap.entrySet()) {
            NavigableSet<LocalDateTime> eventTime = entry.getValue().getAirDates();
            for (LocalDateTime time : eventTime) {
                if (time.isAfter (from) && time.isBefore(to)){
                    events.add(entry.getValue());
                    break;
                }
            }
        }
        return events.isEmpty() ? null : events;
    }

    public Collection<Event> getNextEvents (LocalDateTime to) {
        return getForDateRange(LocalDateTime.now(), to);
    }
}
