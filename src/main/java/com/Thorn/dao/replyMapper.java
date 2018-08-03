package com.Thorn.dao;

import com.Thorn.model.reply;

import java.util.List;

public interface replyMapper {
    int insert(reply record);

    int insertSelective(reply record);

    List<reply> getReplyTop(int id, int page);

    List<reply> getReplySub(int id, int page);

    int getReplyNum(int id);

    reply getTop(int id);
}