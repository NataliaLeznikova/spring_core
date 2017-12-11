package ua.epam.spring.hometask.discountStrategies;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingServiceImpl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;

import static ua.epam.spring.hometask.repository.UserRepository.usersMap;

public class EveryTenthTicketStrategy extends Strategy{

    public double[] ticketPrices = null;

    private double[] getTicketsPrice (@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats, long numberOfTickets) {

        ticketPrices = new double[(int) numberOfTickets];
        for (int i = 0; i < ticketPrices.length; i++)
            for (Long seat : seats) {
                double cost = BookingServiceImpl.costByEventRating(event);
                if (event.getAuditoriums().get(dateTime).getVipSeats().contains(seat)) cost *= 2;
                    ticketPrices[i] = cost;
            }
            return ticketPrices;
    }


    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets, @Nonnull Set<Long> seats ) {

        if (numberOfTickets < 10) return 0;


        else {
            double priceWithoutDiscount = 0;
            double priceAfterDiscount = 0;

            for (int i = 0; i < getTicketsPrice(event, airDateTime, user, seats, numberOfTickets).length; i++) {
                priceWithoutDiscount = priceWithoutDiscount + ticketPrices[i];
            }

            for (int i = 0; i < getTicketsPrice(event, airDateTime, user, seats, numberOfTickets).length; i += 10) {
                ticketPrices[i] = ticketPrices[i] * 0.5;
                priceAfterDiscount = priceAfterDiscount + ticketPrices[i];
            }

            byte discount = (byte) ((priceWithoutDiscount - priceAfterDiscount) * 100 / priceWithoutDiscount);

            return discount;
        }

    }
}
