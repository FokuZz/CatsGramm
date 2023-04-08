package ru.yandex.practicum.catsgram.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.catsgram.exception.*;

@RestControllerAdvice("ru.yandex.practicum.catsgram.controller")
public class ErrorHandler {

    @ExceptionHandler({PostNotFoundException.class, UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundException(RuntimeException e) {
        return new ErrorResponse("Ошибка поиска", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse alreadyExist(UserAlreadyExistException e) {
        return new ErrorResponse("Ошибка уже сущетвует", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invalidParameter(IncorrectParameterException e) {
        return new ErrorResponse("Ошибка с параметром", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse invaildEmail(InvalidEmailException e) {
        return new ErrorResponse("Ошибка со значением email", e.getMessage());
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse error(Throwable e){
        return new ErrorResponse("Произошла непредвиденная ошибка.", e.getMessage());
    }


    @AllArgsConstructor
    @Getter
    static class ErrorResponse {
        private String error;

        private String errorMessage;
    }
}

