package com.Thorn.dao;

import com.Thorn.model.author;

import java.util.List;

public interface authorMapper {
    int insert(author record);

    int insertSelective(author record);

    List<author> findAllAuthors();

    author findOneAuthor(int id);

    int insertAuthor(author author);

    int deleteAuthor(int id);
}