package com.Thorn.dao;

import com.Thorn.model.user;
import com.Thorn.model.userWithBLOBs;

import java.util.List;

public interface userMapper {
    int insert(userWithBLOBs record);

    int insertSelective(userWithBLOBs record);

    user getSimpleInfo(String username);

    List<user> findAllUser();

    user findOneUser(int id);

    int deleteUser(int userId);

    int InsertUser(user user);

    userWithBLOBs login(String username);

    int updateUser(user user);

    int updatePwd(user user);

    int updatePhoto(user user);

    List<user> getUserByPage(int i, int j);

    void updateHistory(userWithBLOBs userWithBLOBs);

    List<user> findoneID(String idenity);

    List<user> findusername(String username);

    void updateMyPostnum(userWithBLOBs user);

    void updateCollections(userWithBLOBs user);
}