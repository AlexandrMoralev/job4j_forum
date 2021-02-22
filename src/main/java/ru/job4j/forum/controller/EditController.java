package ru.job4j.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

@Controller
public class EditController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    public EditController(PostService postService,
                          UserService userService
    ) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String index(@RequestParam(required = false) String error,
                        @RequestParam(required = false) String comment,
                        @RequestParam(required = false) Integer id,
                        Model model
    ) {
        ofNullable(error).ifPresent(e -> model.addAttribute("errorMessage", "Topic creation error"));

        ofNullable(id).flatMap(postService::findById).ifPresent(p -> model.addAttribute("oldPost", p));

        ofNullable(comment).ifPresent(a -> model.addAttribute("comment", a));

        model.addAttribute("action", "/create");
        return "edit";
    }

    @PostMapping("/create")
    public String createPost(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String author,
                             @RequestParam(required = false) Integer oldPostId,
                             @RequestParam(required = false) String comment
    ) {
        long validParams = Stream.of(name, description, author).filter(Objects::nonNull).count();
        if (validParams < 3) {
            return "redirect:/create?error=true";
        }
        User user = ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(p -> ((UserDetails) p).getUsername())
                .flatMap(this.userService::findByLogin)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = Post.of(name, description, user);

        if (oldPostId != null) {
            post.setId(oldPostId);
            if (comment != null) {
                Comment newComment = Comment.of(comment, post.getId(), user);
                post.addComment(newComment);
            }
            this.postService.updatePost(post);
            return "redirect:/post?id=" + oldPostId;
        } else {
            Post newPost = this.postService.addPost(post);
            return "redirect:/post?id=" + newPost.getId();
        }
    }

}
