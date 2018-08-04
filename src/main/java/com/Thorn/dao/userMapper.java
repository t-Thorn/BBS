package com.Thorn.dao;

import com.Thorn.model.user;
import com.Thorn.model.userWithBLOBs;

import java.util.List;

public interface userMapper {
    int insert(userWithBLOBs record);
    user getSimpleInfo(String username);
    userWithBLOBs login(String username);

    void updateHistory(userWithBLOBs userWithBLOBs);

    void updateMyPostnum(userWithBLOBs user);

    void updateCollections(userWithBLOBs user);

    List<userWithBLOBs> findAllUser();

    user findOneUser(int id);

    int deleteUser(int userId);

    int InsertUser(userWithBLOBs user);

    int updateUser(userWithBLOBs user);

    int updatePwd(userWithBLOBs user);

    int updatePhoto(userWithBLOBs user);

    List<userWithBLOBs> getUserByPage(int i, int j);

    List<userWithBLOBs> getUserByPageName(String name, int i, int j);

    List<userWithBLOBs> findusername(String username);

    List<userWithBLOBs> findsomeUser(String username);

    int updatePostnum(String username);

    int deleteUser(String username);

    List<userWithBLOBs> findoneID(String idenity);

    List<userWithBLOBs> findoneIDAndName(String name, String idenity);

    int updateCollect(String Collect, String username);
}