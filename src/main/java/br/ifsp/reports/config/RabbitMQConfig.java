package br.ifsp.reports.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
//import com.rabbitmq.client.AMQP.Queue;
import org.springframework.amqp.core.Queue;;

@Configuration
public class RabbitMQConfig {
  public static final String QUEUE_NAME = "reservation.queue";
  public static final String EXCHANGE_NAME = "reservation.exchange";
  public static final String ROUTING_KEY = "reservation.create";

  @Bean
  public Queue reservationQueue() {
    return new Queue(QUEUE_NAME, true);
  }

  @Bean
  public DirectExchange reservationExchange() {
    return new DirectExchange(EXCHANGE_NAME);
  }

  @Bean
  public Binding reservationBinding(Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
  }
}
