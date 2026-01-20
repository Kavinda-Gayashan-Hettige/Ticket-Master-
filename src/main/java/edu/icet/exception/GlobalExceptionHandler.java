package edu.icet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SeatLockedException.class)
    public ResponseEntity<Object> handleSeatLockedException(SeatLockedException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", ex.getMessage());
        body.put("seconds_remaining", ex.getSecondsRemaining());
        body.put("status", "LOCKED");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }
}