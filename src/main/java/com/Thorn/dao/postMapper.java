package com.Thorn.dao;

import com.Thorn.model.post;

public interface postMapper {
    int insert(post record);

    int insertSelective(post record);
}