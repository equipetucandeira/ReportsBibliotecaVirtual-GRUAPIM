package br.ifsp.reports.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEvent {
    private Long reservationId;
    private Long bookId;
    private String bookTitle;
    private Long userId;
    private LocalDate reservationStartDate;
    private LocalDate reservationEndDate;

    
}

