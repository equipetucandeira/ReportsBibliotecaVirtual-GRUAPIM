package br.ifsp.reports.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsp.reports.dto.LibUseDTO;
import br.ifsp.reports.dto.MostBorrowedDTO;
import br.ifsp.reports.repository.ReservationRepository;

@Service
public class ReportService {
  @Autowired
  ReservationRepository reservationRepository;

  public List<MostBorrowedDTO> reportLoans() {
    return reservationRepository.findMostBorrowedBooks();
  }

  public LibUseDTO reportLibUse(LocalDate startDate, LocalDate endDate) {
    LibUseDTO dto = new LibUseDTO();
    dto.setTotalLoans(reservationRepository.countByStartDateBetween(startDate, endDate));
    dto.setTotalUsers(reservationRepository.countDistinctUsersByStartDateBetween(startDate, endDate));

    List<MostBorrowedDTO> books = reservationRepository.findMostBorrowedBooksByPeriod(startDate, endDate);
    dto.setMostLoans(books.stream().findFirst().orElse(null));

    return dto;
  }
}
