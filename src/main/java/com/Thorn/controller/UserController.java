package com.Thorn.controller;

import com.Thorn.model.user;
import com.Thorn.model.userWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("userSession")
public class UserController {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
    @Autowired
    com.Thorn.dao.userMapper userMapper;

    @RequestMapping("/user/user")
    public String jump() {
        return "/Login/login";
    }

    @PostMapping(value = "/user/reg")
    public String InsertUser(HttpServletRequest request, Model model) {
        List<user> users;

        user user = new user();

        users = userMapper.findusername(request.getParameter("username"));

        if (users.size() <= 0) {
            if (request.getParameter("username").length() <= 18 && request.getParameter("password").length() <= 18) {
                if (request.getParameter("password").equals(request.getParameter("repassword"))) {
                    users = userMapper.findoneID(request.getParameter("identity"));
                    if (users.size() <= 0) {
                        user.setUsername(request.getParameter("username"));
                        user.setPassword(request.getParameter("password"));
                        user.setIdentity(request.getParameter("identity"));
                        user.setName(request.getParameter("name"));
                        user.setRegdate(new Date());
                        userMapper.InsertUser(user);
                        model.addAttribute("tip", "注册成功");
                    } else {
                        model.addAttribute("tip", "身份证重复");
                    }
                } else {
                    model.addAttribute("tip", "两次密码不相同");
                }
            } else {
                model.addAttribute("tip", "用户名和密码不能超过18位重复");
            }
        } else {
            model.addAttribute("tip", "用户名重复");
        }
        return "/Login/login";
    }

    @PostMapping(value = "/user/login", produces = "application/json")
    public String login(HttpServletRequest request, Model model) {
        HttpSession hs = request.getSession();
        userWithBLOBs user;
        String username = request.getParameter("username");
        System.out.println(username);
        String password = request.getParameter("password");
        user = userMapper.login(username);
        if (password.equals(user.getPassword())) {
            model.addAttribute("userSession", user);
            if (user.getLevel() == 1) {
                return "redirect:/BBS/page";
            } else {
                return "redirect:/BBS/page";
            }
        } else {
            model.addAttribute("tip", "用户名或密码错误");
            return "forward:/user/user";
        }
    }

    @PostMapping(value = "user/update")
    public String update(HttpServletRequest request, @ModelAttribute("userSession") user user, Model model) {
        HttpSession hs = request.getSession();
        user.setUsername(hs.getAttribute("username").toString());
        user.setName(request.getParameter("name"));
        user.setAge(Integer.parseInt(request.getParameter("age")));
        user.setIdentity(request.getParameter("identity"));
        user.setGender(request.getParameter("sex"));
        model.addAttribute("userSession", user);
        userMapper.updateUser(user);
        model.addAttribute("tip", "修改成功");
        return "/proto2/setting";
    }

    @PostMapping(value = "user/updatepwd")
    @ResponseBody
    public String updatepwd(HttpServletRequest request, @ModelAttribute("userSession") user user, Model
            model) {
        user user1 = new user();
        HttpSession hs = request.getSession();
        user1.setUsername(hs.getAttribute("username").toString());
        user1 = userMapper.login(user1.getUsername());
        if (request.getParameter("currentpwd").equals(user1.getPassword())) {
            if (request.getParameter("newpwd").equals(request.getParameter("confirmpwd"))) {
                user1.setPassword(request.getParameter("newpwd"));
                userMapper.updatePwd(user1);
                user = user1;
                model.addAttribute("userSession", user);
                model.addAttribute("tip", "修改成功");
                return "/proto2/upload_password";
            } else {
                model.addAttribute("tip", "两次输入不一致");
                return "/proto2/upload_password";
            }
        } else {
            model.addAttribute("tip", "密码错误");
            return "/proto2/upload_password";
        }
    }


    @PostMapping(value = "user/updatephoto")
    @ResponseBody
    public String updatephoto(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                              @ModelAttribute("userSession") user user, Model model) throws IOException {
        if (!file.isEmpty()) {
            user.setPhoto(request.getAttribute("username").toString() + ".jpg");
            String filename = request.getAttribute("username").toString() + ".jpg";
            System.out.println("/photo/" + filename);
            file.transferTo(new File("/photo/" + File.separator + filename));
            System.out.println("second");
            userMapper.updatePhoto(user);
            model.addAttribute("tip", "修改成功");
            model.addAttribute("photo", user.getPhoto());
            return "/proto2/uploadImg";
        } else {
            model.addAttribute("tip", "请选择图片");
            return "/proto2/uploadImg";
        }
    }


    @GetMapping(value = "user/select", produces = "application/json")
    public String findAllUser(HttpServletRequest request, String
            page, Model model) {
        List<user> user = userMapper.findAllUser();
        int pageSize = 7;
        request.setAttribute("userNum", user.size());
        int pageTimes;
        if (user.size() % pageSize == 0) {
            pageTimes = user.size() / pageSize;
        } else {
            pageTimes = user.size() / pageSize + 1;
        }
        request.getSession().setAttribute("pageTimes", pageTimes);
        model.addAttribute("pageTimes", pageTimes);

        if (null == page) {
            page = "1";
        }

        int startRow = (Integer.parseInt(page) - 1) * pageSize;
        user = userMapper.getUserByPage(startRow, pageSize);
        model.addAttribute("currentPage", Integer.parseInt(page));
        model.addAttribute("user", user);
        request.getSession().setAttribute("user", user);
        return "/proto/user";
    }

}
