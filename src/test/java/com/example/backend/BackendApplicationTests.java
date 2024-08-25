package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.backend.entity.Comment;
import com.example.backend.service.CommentService;

@SpringBootTest
class BackendApplicationTests {
	@Autowired
	CommentService commentService;

	@Test
	void contextLoads() {
	}

	@Test
	public void selectComment() {
		Comment comment = commentService.findById(1);
		System.out.println(comment);
	}

	@Test
	public void updateComment() {
		Comment comment = new Comment();
		comment.setId(1);
		comment.setContent("junit測試");
		comment.setAuthor("junit作者");
		comment.setFkId(666);
		commentService.updateComment(comment);
	}

}
