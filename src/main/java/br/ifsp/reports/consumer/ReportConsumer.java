package br.ifsp.reports.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.ifsp.reports.config.RabbitMQConfig;
import br.ifsp.reports.dto.ReservationEvent;

@Component
public class ReportConsumer {
@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
  public void listenReservationQueue(ReservationEvent event){
    System.out.println(event);
  }
}
