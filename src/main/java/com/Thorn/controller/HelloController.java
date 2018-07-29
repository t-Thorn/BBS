package com.Thorn.controller;

import com.Thorn.model.UserTest;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;

@Controller
public class HelloController {

    @RequestMapping(value = "/reg", method = RequestMethod.POST, produces = {"text/html;" +
            "charset=utf-8"})
    @ResponseBody
    public String welcome(@ModelAttribute("user") UserTest userTest, BindingResult
            bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            model.addAttribute("tip", "<h3 style='color:red'>输入错误</h3><br>");
            return "";
        }
        System.out.println("noerror");
        //todo:打算阿瑟东埃是
        return "";
    }

    @GetMapping("/")
    public String hi(@ModelAttribute("user") UserTest userTest, BindingResult
            bindingResult, Model model) {
        return "/OA/index";
    }

}
