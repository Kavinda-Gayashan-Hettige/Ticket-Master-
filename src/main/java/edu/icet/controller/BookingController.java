package edu.icet.controller;

import edu.icet.dto.HoldRequest;
import edu.icet.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/{id}/hold")
    public ResponseEntity<String> holdSeat(@PathVariable Long id, @RequestBody HoldRequest request) {
        bookingService.holdSeat(id, request.getUserId());
        return ResponseEntity.ok("Seat held successfully for 10 minutes.");
    }
}