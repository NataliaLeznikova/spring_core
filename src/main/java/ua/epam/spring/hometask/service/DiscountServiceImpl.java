package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.discountStrategies.Strategy;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    public DiscountServiceImpl(List<Strategy> stategies) {
        this.stategies = stategies;
    }

    List<Strategy> stategies;

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte discount = 0;

        for (Strategy strategy : stategies) {
            if (strategy.getDiscount(user, event, airDateTime, numberOfTickets) > discount)
                discount = strategy.getDiscount(user, event, airDateTime, numberOfTickets);
        }
        return discount;
    }
}