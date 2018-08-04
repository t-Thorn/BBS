package com.Thorn.controller;


import com.Thorn.dao.userMapper;
import com.Thorn.model.userWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class UserController_1 {
    @Autowired
    userMapper userMapper;

    @GetMapping(value = "myinform")
    public String myinform() {
        return "proto2/base";
    }

    @GetMapping(value = "tiaozhuan")
    public String tiaozhuan(@RequestParam(value = "name") String name) {
        System.out.println("---------------跳转------------------");
        return name;
    }

    @GetMapping(value = "user/delete", produces = "application/json")
    public String deleteUser(String username) {
        userMapper.deleteUser(username);
        return "redirect:/user/select?search=null";
    }

    @GetMapping(value = "user/updatecollect", produces = "application/json")
    public String collect(@SessionAttribute("user") userWithBLOBs user, @RequestParam("collect")
            String
            Collect, HttpSession hs) {
        System.out.println(Collect);
        user.setCollections(Collect);
        hs.setAttribute("user", user);
        userMapper.updateCollect(Collect, user.getUsername());
        return "redirect:/user/mycollect";
    }

    @GetMapping(value = "user/postnum", produces = "application/json")
    public String deletepost(String username) {
        //System.out.println("user"+username);
        userMapper.updatePostnum(username);
        return "redirect:/OA/Post?search=null";
    }

    //跳转不同
    @GetMapping(value = "user/postnum2", produces = "application/json")
    public String deletepost2(String username) {
        //System.out.println("user"+username);
        userMapper.updatePostnum(username);
        return "redirect:/user/mypost";
    }
}
