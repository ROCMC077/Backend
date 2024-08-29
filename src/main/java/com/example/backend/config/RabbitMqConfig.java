package com.example.backend.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitMqConfig {
	@Bean
	public Exchange fanout_exange() {
		return ExchangeBuilder.fanoutExchange("test_exange").build();
	}
	
	@Bean
	public Queue fanout_queue_email() {
		return new Queue("test_email_queue");
	}
	
	@Bean
	public Queue fanout_queue_sms() {
		return new Queue("test_sms_queue");
	}
	
	@Bean
	public Binding bindingEmail() {
		return BindingBuilder.bind(fanout_queue_email()).to(fanout_exange()).with("").noargs();
	}
	
	@Bean
	public Binding bindingSMS() {
		return BindingBuilder.bind(fanout_queue_sms()).to(fanout_exange()).with("").noargs();
	}
	
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
