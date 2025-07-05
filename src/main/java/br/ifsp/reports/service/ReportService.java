package br.ifsp.reports.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsp.reports.repository.ReportRepository;
import br.ifsp.reports.dto.LibUseDTO;
import br.ifsp.reports.dto.MostBorrowedDTO;

@Service
public class ReportService {
  @Autowired
  ReportRepository repository;

  public List<MostBorrowedDTO> reportLoans() {
    return repository.findMostBorrowedBooks();
  }

  public LibUseDTO reportLibUse(LocalDate startDate, LocalDate endDate) {
    LibUseDTO dto = new LibUseDTO();
    dto.setTotalLoans(repository.countByStartDateBetween(startDate, endDate));
    dto.setTotalUsers(repository.countDistinctUsersByStartDateBetween(startDate, endDate));

    List<MostBorrowedDTO> books = repository.findMostBorrowedBooksByPeriod(startDate, endDate);
    dto.setMostLoans(books.stream().findFirst().orElse(null));

    return dto;
  }

}

