package br.ifsp.reports.client;

import java.util.List;
import java.time.LocalDate;

import br.ifsp.reports.dto.MostBorrowedDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifsp.reports.config.FeignClientConfig;

@FeignClient(name = "reservation-service", url = "${reservation.api.url}", configuration = FeignClientConfig.class)
public interface ReservationClient {
  @GetMapping("/api/admin/reservations/count")
  public Long countReservationsBetweenStartAndEnd(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate);

  @GetMapping("/api/admin/reservations/distinct-users")
  public Long countReservationByDistinctUsers(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate);

  @GetMapping("/api/admin/reservations/most-borrowed-by-period")
  public List<MostBorrowedDTO> getMostBorrowedByPeriod(@RequestParam LocalDate startDate,
      @RequestParam LocalDate endDate);

  @GetMapping("/api/reservations/most-borrowed")
  public List<MostBorrowedDTO> getAllMostBorrowed();

}
