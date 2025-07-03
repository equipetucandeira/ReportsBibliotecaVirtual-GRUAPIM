package br.ifsp.reports.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.ifsp.reports.config.RabbitMQConfig;

@Component
public class ReportConsumer {
@RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
  public void listenReservationQueue(@Payload String message){
    System.out.println(message);
  }
}
