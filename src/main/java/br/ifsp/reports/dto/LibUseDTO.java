package br.ifsp.reports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibUseDTO {
  private Long totalLoans;
  private Long totalUsers;
  private MostBorrowedDTO mostLoans;
}
