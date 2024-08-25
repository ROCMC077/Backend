package com.example.backend.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Comment implements Serializable{
    private Integer id;
    private String content;
    private String author;
    private Integer fkId;
}
