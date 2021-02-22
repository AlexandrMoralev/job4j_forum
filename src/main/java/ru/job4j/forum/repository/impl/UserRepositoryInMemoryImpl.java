package ru.job4j.forum.repository.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.ofNullable;

@Repository
@PropertySource("classpath:application.properties")
public class UserRepositoryInMemoryImpl implements UserRepository {

    private final ConcurrentHashMap<String, User> users;

    public UserRepositoryInMemoryImpl(@Value("${user.repo.init.capacity}") Integer initCapacity,
                                      @Value("${user.repo.load.factor}") Float loadFactor,
                                      @Value("${user.repo.concurrency.level}") Integer concurrencyLevel
    ) {
        this.users = new ConcurrentHashMap<>(initCapacity, loadFactor, concurrencyLevel);
        this.users.put("admin", User.of("admin", "qwerty"));  // TODO remove stubs
        this.users.put("user", User.of("user", "12345"));
    }


    @Override
    public Optional<User> findByLogin(String login) {
        return ofNullable(users.getOrDefault(login, null));
    }

    @Override
    public void save(User user) {
        ofNullable(users.putIfAbsent(user.getLogin(), user))
                .ifPresent(v -> {
                    throw new RuntimeException("User already exists");
                });
    }
}
