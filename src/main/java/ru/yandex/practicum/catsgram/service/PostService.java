package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final UserService UserService;
    private final List<Post> posts = new ArrayList<>();

    public List<Post> findAll() {
        log.debug("Текущее количество постов: {}", posts.size());
        return posts;
    }

    public Post create(@Valid Post post) {
        if(!UserService.findUserByEmail(post.getAuthor())){
            throw new UserNotFoundException("Пользователь "+post.getAuthor()+" не найден");
        }
        posts.add(post);
        return post;
    }
}
