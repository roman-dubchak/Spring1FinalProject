package com.gb.hw_lesson12_finalproject.exeption;

import com.gb.hw_lesson12_finalproject.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

//аннотация, чтобы ловить все исключения
@ControllerAdvice
public class WebErrorHandler {

    @ExceptionHandler
    @ResponseBody // Thymeleaf думает, что мы возвращаем HTML страницу, а не ответ
    public ResponseEntity <ErrorDto> handlerProductNotFoundException (ProductNotFoundException e){
        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseBody // Thymeleaf думает, что мы возвращаем HTML страницу, а не ответ
    public ResponseEntity <ErrorDto> handlerPersonNotFoundException (PersonNotFoundException e){
        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseBody // Th думает, что мы возвращаем HTML страницу, а не ответ
    public ResponseEntity <ErrorDto> handlerNotSuchElementException (NoSuchElementException e){
        return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST) // Второй вариант, возвращает 400, вместо 200.
    @ResponseBody
    public ResponseEntity<Map<String, String>> handlerMethodArgumentNotValidException (MethodArgumentNotValidException e){
        Map<String, String> errors= new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(objectError -> { // getBindingResult собирает все Exception
            String fieldName = ((FieldError) objectError).getField();
            String errorMassage = objectError.getDefaultMessage();
            errors.put(fieldName, errorMassage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
