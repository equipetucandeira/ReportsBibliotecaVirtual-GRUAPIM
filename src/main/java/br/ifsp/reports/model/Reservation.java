package br.ifsp.reports.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservation")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long bookId;
  private String bookTitle;
  private Long userId;
  public void setUserId(Long userId) {
	this.userId = userId;
}

private LocalDate startDate;
  private LocalDate endDate;

  public Reservation() {

  }

  public Reservation(Long bookId, String bookTitle, Long userId, LocalDate startDate, LocalDate endDate) {
    this.bookId = bookId;
    this.bookTitle = bookTitle;
    this.userId = userId;
    this.startDate = startDate;
    this.endDate = startDate.plusDays(7);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long book) {
    this.bookId = book;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String book) {
    this.bookTitle = book;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUser(Long user) {
    this.userId = user;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
}
