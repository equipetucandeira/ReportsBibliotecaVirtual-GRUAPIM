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
    private Long userId;
    private LocalDate reservationDateBegin;
    private LocalDate reservationDateEnd;

    
}

