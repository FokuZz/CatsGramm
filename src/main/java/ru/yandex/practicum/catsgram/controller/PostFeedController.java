package ru.yandex.practicum.catsgram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostFeedController {

    private final PostService service;
    ObjectMapper objectMapper = new ObjectMapper();

    FriendsList friendsList;

    @PostMapping("/feed/friends")
    public Collection<Post> getFriendsFeed(@RequestBody String text) {
        try {
            String friendString = objectMapper.readValue(text, String.class);
            friendsList = objectMapper.readValue(friendString, FriendsList.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Non-valid format json",e);
        }

        if(friendsList != null){
            List<Post> result = new ArrayList<>();
            for(String friendEmail: friendsList.friends){
                result.addAll(service.findAllByUserEmail(friendEmail, friendsList.size, friendsList.sort));
            }

            return result;
        } else {
            throw new RuntimeException("Parameters are filled in incorrectly");
        }

    }
    @Data
    static class FriendsList {
        private String sort;

        private Integer size;

        private List<String> friends;
    }
}


