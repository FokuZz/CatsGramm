package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.IncorrectParameterException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping("/posts")
    public Collection<Post> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                    @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {

        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IncorrectParameterException("Введён неправильный параметр sort = " + sort);
        }
        if (size <= 0) {
            throw new IncorrectParameterException("Параметр size не может быть негативным = " + size);
        }
        if (page < 0) {
            throw new IncorrectParameterException("Параметр page не может быть негативным = " + page);
        }
        Integer from = page * size;
        return service.findAll(sort, size, from);
    }

    @GetMapping("/posts/{postId}")
    public Post findId(@PathVariable int postId) {
        return service.findId(postId);
    }

    @PostMapping("/post")
    public Post create(@Valid @RequestBody Post post) {
        return service.create(post);
    }
}