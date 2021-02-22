package ru.job4j.forum.service;

import ru.job4j.forum.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostService {

    Collection<Post> getAllPosts();

    Optional<Post> findById(Integer id);

    Post addPost(Post post);

    void updatePost(Post post);

    Collection<Post> getUserPosts(Integer userId);
}
