package com.Thorn.controller;

import java.util.List;

import com.Thorn.dao.blogMapper;
import com.Thorn.model.blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class BlogController {

    @Autowired
    blogMapper blogMapper;

    @GetMapping("/blogs")
    @ResponseBody
    public List<blog> findAllBlogs() {
        return blogMapper.findAllBlogs();
    }

}
