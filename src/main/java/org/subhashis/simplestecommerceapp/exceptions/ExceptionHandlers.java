package org.subhashis.simplestecommerceapp.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFound(ProductNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleOrderNotFound(OrderNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handle(ConstraintViolationException e) {
        List<String> errors = e.getConstraintViolations()
                .stream()
                .map(v -> v.getMessage())
                .collect(Collectors.toList());
        var apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), errors, LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(apiError);
    }
}
