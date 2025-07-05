package br.ifsp.reports.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifsp.reports.dto.ReservationEvent;
import br.ifsp.reports.model.Reservation;

public interface ReservationReportRepository extends JpaRepository<Reservation, Long> {

}

