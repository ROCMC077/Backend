package com.example.backend.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.backend.entity.Comment;
import com.example.backend.mapper.CommentMapper;
import com.example.backend.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentMapper commentMapper;
	@Autowired
	RedisTemplate redisTemplate;
	
	@Override
	public Comment findById(Integer id) {
		//先從redis獲取, 沒有的話就去資料庫查並存到redis
		Object obj = redisTemplate.opsForValue().get(id);
		
		if(obj!=null) {
			System.err.println("從redis找");
			return (Comment)obj;
		}else {
			System.err.println("從資料庫找, 並把數據存到redis");
			Comment comment = commentMapper.findById(id);
			redisTemplate.opsForValue().set(id,comment);
			return comment;

		}
	}

	@Override
	public Integer updateComment(Comment comment) {
		Integer result = commentMapper.updateComment(comment);
		// 刪除Redis中的緩存
		redisTemplate.delete(comment.getId());
		return result;
	}
	
	

}
