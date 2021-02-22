package ru.job4j.forum.repository.impl;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryInMemoryImpl implements PostRepository {

    private final AtomicInteger ids = new AtomicInteger(1);

    private final List<Post> posts = new CopyOnWriteArrayList<>();

    public PostRepositoryInMemoryImpl() {
        User user = User.of("user", ""); // TODO remove stubs
        posts.addAll(
                Arrays.asList(
                        new Post(ids.getAndIncrement(), "Объявление", "Продаю машину ладу 07.", user, Collections.singletonList(Comment.of("Что почем?", ids.get(), user))),
                        new Post(ids.getAndIncrement(), "Реклама", "Щаурма", user, List.of(Comment.of("пицца лучше", ids.get(), user), Comment.of("а мне норм", ids.get(), user))),
                        new Post(ids.getAndIncrement(), "Наброс", "Доколе?", user, List.of(Comment.of("пора валить", ids.get(), user), Comment.of("хватит ныть", ids.get(), user), Comment.of("а в Америке линчуют", ids.get(), user), Comment.of("под кандагаром было круче11", ids.get(), user)))
                )
        );
    }

    @Override
    public List<Post> getAllPosts() {
        return new ArrayList<>(posts);
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return this.posts.stream().filter(p -> id.equals(p.getId())).findAny();
    }

    @Override
    public Post save(Post post) {
        return Optional.of(post)
                .map(p -> {
                    p.setId(ids.getAndIncrement());
                    this.posts.add(p);
                    return p;
                }).orElseThrow(() -> new RuntimeException("An error occurred while saving the post"));
    }

    @Override
    public void updatePost(Post post) {
        Optional.of(post)
                .filter(p -> p.getId() != null)
                .ifPresent(
                        updated -> findById(updated.getId())
                                .ifPresent(
                                        current -> {
                                            posts.remove(current);
                                            posts.add(updated);
                                        }
                                )
                );
    }

    @Override
    public Collection<Post> getUserPosts(Integer userId) {
        return this.posts.stream().filter(p -> p.getAuthor().getId().equals(userId)).collect(Collectors.toList());
    }
}
