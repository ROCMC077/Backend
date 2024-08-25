package com.example.backend.service;

import com.example.backend.entity.Comment;

public interface CommentService {
	public Comment findById(Integer id);

	public Integer updateComment(Comment comment);
}
