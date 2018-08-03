package com.Thorn.dao;

import com.Thorn.model.post;
import com.Thorn.model.reply;

import java.util.List;

public interface postMapper {
    int insert(post record);

    int insertSelective(post record);

    List<post> findPost(String content, int page);//-1*10

    List<post> findEssencePost();

    int getPostNum(String content);

    List<post> findHotPost();

    List<post> findTopPost();

    post findThePost(int id);

    void updateViews(int id);

    int findNewPostID();
}