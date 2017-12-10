package ua.epam.spring.hometask.repository;

import ua.epam.spring.hometask.domain.Auditorium;

import java.util.HashMap;
import java.util.Map;

public abstract class AuditoriumRepository {

    public static Map<Long, Auditorium> auditoriesMap = new HashMap<>();
}
