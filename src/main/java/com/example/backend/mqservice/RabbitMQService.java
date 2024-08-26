package com.example.backend.mqservice;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.backend.entity.Comment;

@Service
public class RabbitMQService {

	@Autowired
	RestTemplate restTemplate;
	
	
	@RabbitListener(queues = "test_sms_queue")
	public void psubConsumerSMS(Comment comment) {
		restTemplate.postForObject("http://127.0.0.1:8080/api/updateComment", comment, String.class);
	}
}
