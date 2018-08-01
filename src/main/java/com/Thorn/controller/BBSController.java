package com.Thorn.controller;

import com.Thorn.dao.postMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@SessionAttributes("trueContent")
public class BBSController {
    @Autowired
    postMapper postMapper;
    Logger logger = Logger.getLogger(this.getClass());

    @GetMapping("/")
    public String index() {
        return "forward:/BBS/page";
    }


    @RequestMapping("/BBS/page")
    public String getPost(@ModelAttribute("param") int param,
                          @ModelAttribute("content") String content
            , @ModelAttribute("pages") int pages, Model model) {
        //ModelAndView modelAndView=new ModelAndView();
        //logger.info("page:"+pages+" param:"+param);
        //logger.warn(postMapper.findPost(content, (param - 1) * 10).get(0).getPosttime());
        if (pages == 0) {
            return "/BBS/index";
        }
        model.addAttribute("posts", postMapper.findPost(content, (param - 1) * 10));
        return "/BBS/index";
    }

    @ModelAttribute
    public void inPublicMethod(Model
                                       model) {
        model.addAttribute("EssencePosts", postMapper.findEssencePost());
        model.addAttribute("HotPosts", postMapper.findHotPost());
        model.addAttribute("TopPosts", postMapper.findTopPost());
    }

    @ModelAttribute
    public void Preprocess(@RequestParam(required = false, defaultValue = "1") int param,
                           @RequestParam(required = false, defaultValue = "0") int method,
                           @RequestParam(required = false, defaultValue = "") String content,
                           Model model) {
        if (method == 0) {
            Map<String, Object> map = model.asMap();
            content = (String) map.get("trueContent");
            //不然会出bug
            if (content == null)
                content = "";
            //logger.warn(content);
        }
        int pages = 0;
        pages = postMapper.getPostNum(content);

        if (pages % 10 != 0) {
            pages = pages / 10;
            pages++;
        } else
            pages = pages / 10;

        logger.info("page:" + pages + " param:" + param);
        if (pages == 0) {
            logger.warn("无结果");
            model.addAttribute("nowpage", 0);
            model.addAttribute("searchError", "无结果");
        } else if (pages < param) {
            logger.warn("page:" + pages);
            model.addAttribute("searchError", "页面超出范围");
            param = pages;
        }
        model.addAttribute("param", param);
        model.addAttribute("nowpage", param);
        model.addAttribute("pages", pages);
        model.addAttribute("content", content);
        model.addAttribute("trueContent", content);
    }
}
