package edu.icet.exception;

import lombok.Getter;

@Getter
public class SeatLockedException extends RuntimeException {
    private final long secondsRemaining;

    public SeatLockedException(long secondsRemaining) {
        super("Seat is currently locked by another user.");
        this.secondsRemaining = secondsRemaining;
    }
}