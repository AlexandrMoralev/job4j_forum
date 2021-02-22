package ru.job4j.forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.service.PostService;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository posts;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.posts = postRepository;
    }

    @Override
    public Collection<Post> getAllPosts() {
        return posts.getAllPosts();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return this.posts.findById(id);
    }

    @Override
    public Post addPost(Post post) {
        return posts.save(post);
    }

    @Override
    public void updatePost(Post post) {
        posts.updatePost(post);
    }

    @Override
    public Collection<Post> getUserPosts(Integer userId) {
        return posts.getUserPosts(userId);
    }
}
