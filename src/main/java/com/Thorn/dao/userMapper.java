package com.Thorn.dao;

import com.Thorn.model.userWithBLOBs;

public interface userMapper {
    int insert(userWithBLOBs record);

    int insertSelective(userWithBLOBs record);


}