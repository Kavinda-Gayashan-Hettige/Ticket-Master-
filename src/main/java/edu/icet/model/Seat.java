package edu.icet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Long heldByUserId;

    private LocalDateTime holdExpiry;


    @Version
    private Integer version;


    public boolean isHoldExpired() {
        return holdExpiry != null && LocalDateTime.now().isAfter(holdExpiry);
    }
}