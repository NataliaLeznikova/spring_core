package ua.epam.spring.hometask.repository;

import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class TicketRepository {

    public static Map<User, Set<Ticket>> ticketsMap = new HashMap<>();
}
