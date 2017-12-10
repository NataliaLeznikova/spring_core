package ua.epam.spring.hometask.repository;

import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;

public abstract class UserRepository {

    public static Map<Long, User> usersMap = new HashMap<>();
}
