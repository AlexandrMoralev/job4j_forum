package ru.job4j.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Controller
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public String getPost(@RequestParam(required = false) Integer id,
                          Model model
    ) {
        Optional<Post> post = ofNullable(id).flatMap(postService::findById);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "post";
        } else {
            return "redirect:/";
        }
    }

}
