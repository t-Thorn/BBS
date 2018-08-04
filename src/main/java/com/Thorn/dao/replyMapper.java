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

    void newreply(reply reply);

    void delreply(reply reply);

    int getTheSubReplyNum(reply reply);

    void newSubReply(reply reply);

    void addlastfloor(int postid, int floor, int floorex);

    String findTheParentReplyer(reply reply);

    void delSubReply(reply reply);

    int getMaxReplyNum(int id);


}