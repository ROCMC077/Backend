package com.example.backend.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Comment;
import com.example.backend.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@GetMapping("/get/{id}")
	public Comment findById(@PathVariable Integer id) {
		Comment comment = commentService.findById(id);
		return comment;
	}
	
	@PostMapping("/updateComment")
	public Integer updateComment(@RequestBody Comment comment) {
		System.err.println("enter update");
		return commentService.updateComment(comment);
	}
	
	@GetMapping("/sendComment/{id}")
	public Comment send2mq(@PathVariable Integer id) {
		Comment comment = new Comment();
		comment.setId(id);
		comment.setContent("rabbitMQ test");
		comment.setAuthor("rabbit");
		comment.setFkId(id);
		rabbitTemplate.convertAndSend("test_exange","",comment);
		System.err.println("send2mq"+comment+"by producer");
		return comment;
	}
}
