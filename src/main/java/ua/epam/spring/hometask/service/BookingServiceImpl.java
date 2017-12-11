package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.*;

import static ua.epam.spring.hometask.repository.TicketRepository.ticketsMap;

public class BookingServiceImpl implements BookingService {

    DiscountServiceImpl discountService;

    public BookingServiceImpl(DiscountServiceImpl discountService) {
        this.discountService = discountService;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        double ticketsCost = 0;

        for (Long seat : seats) {
            double cost = costByEventRating(event);
            if (event.getAuditoriums().get(dateTime).getVipSeats().contains(seat)) {
                cost *= 2;
            }
            ticketsCost += cost;
        }
        return ticketsCost * (1 - (discountService.getDiscount(user, event, dateTime, seats.size())/100));
    }


    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            if (ticket.getUser() != null) {
                saveTicket(ticket);
            } else {
                ticket.setUser(new User());
                saveTicket(ticket);
            }
        }

    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Set<Ticket> purchasedTickets = new HashSet<>();

        for (Map.Entry<User, Set<Ticket>> entry : ticketsMap.entrySet()) {
            for (Ticket ticket : entry.getValue()) {
                if (ticket.getEvent().equals(event) && ticket.getDateTime().compareTo(dateTime) == 0)
                    purchasedTickets.add(ticket);
            }
        }
        return purchasedTickets;
    }

    private void saveTicket(@Nonnull Ticket ticket) {
        if (ticketsMap.get(ticket.getUser()) != null)
            ticketsMap.get(ticket.getUser()).add(ticket);
        else ticketsMap.put(ticket.getUser(), Collections.singleton(ticket));
    }

    public static double costByEventRating(@Nonnull Event event) {
        double cost = event.getBasePrice();

        switch (event.getRating()) {
            case LOW: return cost;
            case MID: return cost * 1.3;
            case HIGH: return cost * 1.7;
            default: return 0;
        }
    }
}
