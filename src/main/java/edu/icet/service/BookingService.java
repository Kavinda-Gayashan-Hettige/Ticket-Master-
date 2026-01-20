package edu.icet.service;

import edu.icet.exception.SeatLockedException;
import edu.icet.model.Seat;
import edu.icet.model.SeatStatus;
import edu.icet.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final SeatRepository seatRepository;

    @Transactional
    public void holdSeat(Long seatId, Long userId) {

        Seat seat = seatRepository.findByIdWithLock(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        LocalDateTime now = LocalDateTime.now();


        if (seat.getStatus() == SeatStatus.SOLD) {
            throw new RuntimeException("Seat is already sold!");
        }


        if (seat.getStatus() == SeatStatus.HELD) {

            if (seat.getHoldExpiry() != null && now.isBefore(seat.getHoldExpiry())) {
                long secondsLeft = Duration.between(now, seat.getHoldExpiry()).getSeconds();
                throw new SeatLockedException(secondsLeft);
            }
        }


        seat.setStatus(SeatStatus.HELD);
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(now.plusMinutes(10));

        seatRepository.save(seat);
    }
}