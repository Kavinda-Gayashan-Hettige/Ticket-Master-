package edu.icet.service;

import edu.icet.model.Seat;
import edu.icet.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;


    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }


    public Seat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seat not found with ID: " + id));
    }
}