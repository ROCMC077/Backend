package com.example.backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.backend.entity.Comment;

@Mapper
public interface CommentMapper {
    @Select("select * from t_comment where id = #{id}")
    @Results({
        @Result(property = "fkId", column = "fk_id"),
    })
    public Comment findById(Integer id);

    @Update("update t_comment SET content=#{content}, author=#{author}, fk_id=#{fkId} WHERE id = #{id}")
	public Integer updateComment(Comment comment);
}
