package com.Thorn.controller;

import java.util.List;

import com.Thorn.dao.authorMapper;
import com.Thorn.model.author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthorController {

    @Autowired
    authorMapper authorMapper;

    @GetMapping(value = "/authors", produces = "application/json")
    @ResponseBody
    public List<author> findAllAuthors() {
        System.out.println("in!!");
        return authorMapper.findAllAuthors();

    }

    @GetMapping(value = "/authors/{id}", produces = "application/json")
    @ResponseBody
    public author findOneAuthor(@PathVariable int id) {
        return authorMapper.findOneAuthor(id);

    }


    @GetMapping("authors/add")
    @ResponseBody
    public int insertAuthor() {
        author author = new author();
        author.setName("john");
        author.setPhone("123456");
        int i = authorMapper.insertAuthor(author);

        System.out.println("i = " + i);

        System.out.println(author);

        return i;
    }


    @GetMapping("delauthors/{id}")
    @ResponseBody
    public int deleteCity(@PathVariable int id) {

        int i = authorMapper.deleteAuthor(id);
        return i;
    }

}
