package br.ifsp.reports.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.ifsp.reports.config.RabbitMQConfig;
import br.ifsp.reports.dto.ReservationEvent;
import br.ifsp.reports.model.Reservation;
import br.ifsp.reports.repository.ReservationReportRepository;

@Component
public class ReportConsumer {

  private final ReservationReportRepository repository;

  public ReportConsumer(ReservationReportRepository repository) {
    this.repository = repository;
  }

  @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
  public void listenReservationQueue(ReservationEvent event) {
    System.out.println(event);

    Reservation reservation = new Reservation();
    reservation.setId(event.getReservationId());
    reservation.setBookId(event.getBookId());
    reservation.setBookTitle(event.getBookTitle());
    reservation.setStartDate(event.getReservationStartDate());
    reservation.setEndDate(event.getReservationEndDate());
    
    repository.save(reservation);

  }
}
