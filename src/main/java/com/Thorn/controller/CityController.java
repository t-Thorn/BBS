package com.Thorn.controller;

import com.Thorn.dao.cityMapper;
import com.Thorn.dao.zwe;
import com.Thorn.model.city;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CityController {
    @Autowired
    private cityMapper cityMapper;

    @GetMapping(value = "/cities", produces = "application/json")
    @ResponseBody
    public city findAllCity() {
        city city = cityMapper.findOneCity(1);
        System.out.println(city);
        return cityMapper.findOneCity(1);

    }
}
