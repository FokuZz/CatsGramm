package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping("/posts")
    public Collection<Post> findAll() {
        return service.findAll();
    }

    @GetMapping("/posts/{postId}")
    public Post findId(@PathVariable int postId){
        return service.findId(postId);
    }

    @PostMapping("/post")
    public Post create(@Valid @RequestBody Post post) {
        return service.create(post);
    }
}