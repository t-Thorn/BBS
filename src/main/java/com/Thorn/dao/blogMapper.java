package com.Thorn.dao;

import com.Thorn.model.blog;

import java.util.List;

public interface blogMapper {
    int insert(blog record);

    List<blog> findAllBlogs();

    int insertSelective(blog record);
}