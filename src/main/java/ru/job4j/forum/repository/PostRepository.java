package ru.job4j.forum.repository;

import ru.job4j.forum.model.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostRepository {

    Collection<Post> getAllPosts();

    Optional<Post> findById(Integer id);

    Post save(Post post);

    void updatePost(Post post);

    Collection<Post> getUserPosts(Integer userId);
}
