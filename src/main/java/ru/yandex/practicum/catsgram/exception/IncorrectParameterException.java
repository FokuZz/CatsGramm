package ru.yandex.practicum.catsgram.exception;

public class IncorrectParameterException extends RuntimeException {
    public IncorrectParameterException(String message) {
        super(message);
    }
}
