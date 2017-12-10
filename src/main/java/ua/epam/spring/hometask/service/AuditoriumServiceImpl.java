package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static ua.epam.spring.hometask.repository.AuditoriumRepository.auditoriesMap;

public class AuditoriumServiceImpl implements AuditoriumService{


    public AuditoriumServiceImpl() {
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<>(auditoriesMap.values());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Auditorium auditorium = null;

        for (Map.Entry<Long, Auditorium> entry : auditoriesMap.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                auditorium = entry.getValue();
                break;
            }
        }
        return auditorium;
    }
}
