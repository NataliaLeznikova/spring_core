package ua.epam.spring.hometask.discountStrategies;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingServiceImpl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;


public class BirthdayStrategy extends Strategy{

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets, @Nonnull Set<Long> seats) {
        long daysBetween = ChronoUnit.DAYS.between(user.getBirthday(), airDateTime);
        if (daysBetween > 6) return 0;
        else return 5;
    }
}
