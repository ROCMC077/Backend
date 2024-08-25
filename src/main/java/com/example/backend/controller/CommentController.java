package com.example.backend.controller;

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
	
	@GetMapping("/get/{id}")
	public Comment findById(@PathVariable Integer id) {
		Comment comment = commentService.findById(id);
		return comment;
	}
	
	@PostMapping("/updateComment")
	public Integer updateComment(@RequestBody Comment comment) {
		return commentService.updateComment(comment);
	}
}
