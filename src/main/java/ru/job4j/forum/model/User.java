package ru.job4j.forum.model;

import java.time.LocalDateTime;
import java.util.*;

public class User {

    private Integer id;
    private String login;
    private String password;
    private LocalDateTime registeredAt;
    private Collection<Comment> comments;

    public static User of(String name,
                   String password
    ) {
        User user = new User();
        user.login = name;
        user.password = password;
        user.registeredAt = LocalDateTime.now();
        user.comments = new TreeSet<>();
        return user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Collection<Comment> getComments() {
        return new TreeSet<>(comments);
    }

    public void setComments(Collection<Comment> comments) {
        this.comments.addAll(comments);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id)
                &&  login.equals(user.login)
                && password.equals(user.password)
                && Objects.equals(registeredAt, user.registeredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, registeredAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("login='" + login + "'")
                .add("registeredAt=" + registeredAt)
                .toString();
    }
}
