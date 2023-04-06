package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private int idCounter = 1;

    private final UserService UserService;
    private final HashMap<Integer, Post> posts = new HashMap<>();

    public Collection<Post> findAll() {
        log.debug("Текущее количество постов: {}", posts.size());
        return posts.values();
    }

    public Post findId(int id) {
        if (posts.containsKey(id)) {
            return posts.get(id);
        }
        throw new PostNotFoundException("Такой id = " + id + " не найден");
    }

    public Post create(@Valid Post post) {
        if (!UserService.findUserByEmail(post.getAuthor())) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
        }
        post.setId(idCounter);
        posts.put(idCounter++, post);
        return post;
    }
}
