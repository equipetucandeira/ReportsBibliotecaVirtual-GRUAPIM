package br.ifsp.reports.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifsp.reports.model.Reservation; 
import br.ifsp.reports.dto.MostBorrowedDTO;
import br.ifsp.reports.dto.LibUseDTO;

public interface ReportRepository extends JpaRepository<Reservation, Long> {

  @Query("SELECT new br.ifsp.reports.dto.MostBorrowedDTO(r.bookTitle, COUNT(r)) FROM Reservation r GROUP BY r.bookTitle ORDER BY COUNT(r) DESC")
  List<MostBorrowedDTO> findMostBorrowedBooks();

  @Query("SELECT COUNT(r) FROM Reservation r WHERE r.startDate BETWEEN :start AND :end")
  long countByStartDateBetween(LocalDate start, LocalDate end);

  @Query("SELECT COUNT(DISTINCT r.userId) FROM Reservation r WHERE r.startDate BETWEEN :start AND :end")
  long countDistinctUsersByStartDateBetween(LocalDate start, LocalDate end);

  @Query("SELECT new br.ifsp.reports.dto.MostBorrowedDTO(r.bookTitle, COUNT(r)) " +
      "FROM Reservation r WHERE r.startDate BETWEEN :start AND :end " +
      "GROUP BY r.bookTitle ORDER BY COUNT(r) DESC")
  List<MostBorrowedDTO> findMostBorrowedBooksByPeriod(LocalDate start, LocalDate end);

}
