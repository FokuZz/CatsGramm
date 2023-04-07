package ru.yandex.practicum.catsgram.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private int idCounter = 1;

    private final UserService UserService;
    private final HashMap<Integer, Post> posts = new HashMap<>();

    public Collection<Post> findAll(String sort, int size, int from) {
        log.debug("Текущее количество постов: {}", posts.size());

        return posts.values().stream().sorted((p0, p1) ->{
                int comp = p0.getCreationDate().compareTo(p1.getCreationDate());
                if(sort.equals("desc")){
                    comp = -1 * comp;
                }
                return comp;
        }).skip(from).limit(size).collect(Collectors.toList());
    }

    public Post findId(int id) {
        if (posts.containsKey(id)) {
            return posts.get(id);
        }
        throw new PostNotFoundException("Такой id = " + id + " не найден");
    }

    public List<Post> findAllByUserEmail(String userEmail, Integer size, String sort){
        return posts.values().stream().filter(p -> userEmail.equals(p.getAuthor())).sorted((p1, p2) -> {
            int comp = p1.getCreationDate().compareTo(p2.getCreationDate());
            if(sort.equals("desc")){
                comp = -1 * comp;
            }
            return comp;
        }).limit(size).collect(Collectors.toList());
    }

    public Post create(@Valid Post post) {
        if (UserService.findUserByEmail(post.getAuthor()) == null) {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
        }
        post.setId(idCounter);
        posts.put(idCounter++, post);
        return post;
    }
}
