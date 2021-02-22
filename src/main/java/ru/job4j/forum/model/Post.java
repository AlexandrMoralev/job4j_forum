package ru.job4j.forum.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.TreeSet;

public class Post {

    private Integer id;
    private String name;
    private String description;
    private User author;
//    private Integer authorId;
    private LocalDateTime createdAt;
    private Collection<Comment> comments;

    public Post() {
    }

    //TODO remove after tests
    public Post(Integer id,
                String name,
                String description,
                User user,
                Collection<Comment> comments
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = user;
        this.createdAt = LocalDateTime.now();
        this.comments = new TreeSet<>();
        this.comments.addAll(comments);
    }

    public static Post of(String name,
                          String description,
                          User author
//                          Integer authorId
    ) {
        Post post = new Post();
        post.name = name;
        post.description = description;
        post.author = author;
//        post.authorId = authorId;
        post.createdAt = LocalDateTime.now();
        post.comments = new TreeSet<>();
        return post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
//
//    public Integer getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(Integer authorId) {
//        this.authorId = authorId;
//    }

    public Collection<Comment> getComments() {
        return new TreeSet<>(comments);
    }

    public void setComments(Collection<Comment> comments) {
        this.comments.addAll(comments);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(id, post.id)
                && name.equals(post.name)
                && Objects.equals(description, post.description)
                && Objects.equals(createdAt, post.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, createdAt);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("createdAt=" + createdAt)
                .toString();
    }
}
