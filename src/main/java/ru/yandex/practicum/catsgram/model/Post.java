package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class Post {
    private Integer id;
    @NotNull
    private final String author; // автор
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final Instant creationDate = Instant.now(); // дата создания
    @NotNull
    private String description; // описание
    @NotNull
    private String photoUrl; // url-адрес фотографии

    public Post(String author, String description, String photoUrl) {
        this.photoUrl = photoUrl;
        this.description = description;
        this.author = author;
    }
}