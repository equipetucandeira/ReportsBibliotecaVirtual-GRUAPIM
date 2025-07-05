package br.ifsp.reports.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsp.reports.client.ReservationClient;
import br.ifsp.reports.dto.LibUseDTO;
import br.ifsp.reports.dto.MostBorrowedDTO;


@Service
public class ReportService {
  @Autowired
  ReservationClient reservClient;

  public List<MostBorrowedDTO> reportLoans() {
    return reservClient.getAllMostBorrowed();
  }

  public LibUseDTO reportLibUse(LocalDate startDate, LocalDate endDate) {
    LibUseDTO dto = new LibUseDTO();
    dto.setTotalLoans(reservClient.countReservationsBetweenStartAndEnd(startDate, endDate));
    dto.setTotalUsers(reservClient.countReservationByDistinctUsers(startDate, endDate));

    List<MostBorrowedDTO> books = reservClient.getMostBorrowedByPeriod(startDate, endDate);
    dto.setMostLoans(books.stream().findFirst().orElse(null));

    return dto;
  }
}
