package com.Thorn.dao;

import com.Thorn.model.reply;

public interface replyMapper {
    int insert(reply record);

    int insertSelective(reply record);
}