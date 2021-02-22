package ru.job4j.forum.service;

import ru.job4j.forum.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByLogin(String login);

    void saveUser(User user);

}
