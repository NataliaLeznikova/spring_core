package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.repository.UserRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ua.epam.spring.hometask.repository.UserRepository.usersMap;

public class UserServiceImpl implements UserService{


    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {

        User user = null;

        for (Map.Entry<Long, User> entry : usersMap.entrySet()) {
            if (entry.getValue().getEmail().equals(email)) {
                user = entry.getValue();
                break;
            }
        }
        return user;
    }

    @Override
    public User save(@Nonnull User object) {
        return usersMap.put((long) (usersMap.size() + 1), object);
    }

    @Override
    public void remove(@Nonnull User object) {
        usersMap.values().remove(object);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return usersMap.get(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return new ArrayList<>((usersMap.values()));
    }
}
