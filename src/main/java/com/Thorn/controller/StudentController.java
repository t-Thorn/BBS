package com.Thorn.controller;

//import javax.validation.Valid;

import com.Thorn.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
public class StudentController {

    @GetMapping("/student/reg")
    public String toReg() {
        return "register";
    }

    @PostMapping(value = "/student/doreg")
    @ResponseBody
    public String doReg(@RequestParam String name, @RequestParam int age) {
        Student stu = new Student(name, age);
        System.out.println(stu);
        return "";
    }

    @PostMapping(value = "/student/doreg2")
    public String doReg2(@Valid @ModelAttribute("stu1") Student stu, BindingResult result, Model
            model) {
        if (result.hasErrors()) {
            return "register";
        }
        System.out.println(stu);
        return "regResult";
    }


}
