package ru.job4j.forum.repository;

import ru.job4j.forum.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByLogin(String login);

    void save(User user);
}
