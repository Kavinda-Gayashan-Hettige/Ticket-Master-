package edu.icet.controller;

import edu.icet.model.Seat;
import edu.icet.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
@CrossOrigin
public class SeatController {

    private final SeatService seatService;


    @GetMapping
    public List<Seat> getAllSeats() {
        return seatService.getAllSeats();
    }


    @GetMapping("/{id}")
    public Seat getSeatById(@PathVariable Long id) {
        return seatService.getSeatById(id);
    }
}