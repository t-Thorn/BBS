package com.Thorn.controller;

import com.Thorn.dao.postMapper;
import com.Thorn.dao.replyMapper;
import com.Thorn.dao.userMapper;
import com.Thorn.model.reply;
import com.Thorn.model.userWithBLOBs;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("userSession")
public class BBSDetail {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
    @Autowired
    replyMapper replyMapper;
    @Autowired
    postMapper postmapper;
    @Autowired
    userMapper userMapper;

    @RequestMapping("/BBS/post")
    public String getdetail(@RequestParam(defaultValue = "1") int param, Model model,
                            @RequestParam(required = false, defaultValue = "1") int param1
    ) {
        //获取回复
        List<reply> top = replyMapper.getReplyTop(param, (param1 - 1) * 8);
        List<reply> sub = replyMapper.getReplySub(param, param1 * 8);
        reply firstReply = replyMapper.getTop(param);
        int pages = replyMapper.getReplyNum(param);
        if (pages > 0)
            pages /= 8;
        if (pages == 0)
            pages++;
        model.addAttribute("firstReply", firstReply);
        model.addAttribute("top", top);
        model.addAttribute("sub", sub);
        model.addAttribute("nowpage", param1);
        logger.info(param1);
        model.addAttribute("pages", pages);
        logger.info(pages);
        return "BBS/tiezi";
    }

    @ModelAttribute
    public void Preprocess(@RequestParam(defaultValue = "1") int param, Model model,
                           @ModelAttribute("userSession") userWithBLOBs user
    ) {
        //获取帖子基本信息
        model.addAttribute("post", postmapper.findThePost(param));
        model.addAttribute("owner", userMapper.getSimpleInfo(postmapper.findThePost(param)
                .getUsername()));

        //更新帖子浏览人数
        postmapper.updateViews(param);

        if (user != null) {
            logger.info(user.getUsername());
            if (user.getHistory() == null || user.getHistory().equals(""))
                user.setHistory(String.valueOf(param));
            else {
                String[] historys = user.getHistory().split(";");
                int iscollection = iscollection(historys, param);
                if (historys.length < 30) {
                    if (iscollection == -1) {
                        logger.info("不包含");
                        user.setHistory(user.getHistory() + ";" + param);
                    } else {
                        logger.info("包含");
                        //已经包含这个浏览纪录,则更新
                        StringBuilder newHistorys = new StringBuilder("");
                        for (int index = 0; index < historys.length; index++) {
                            //去旧
                            if (iscollection == index)
                                continue;

                            newHistorys.append(historys[index] + ";");
                        }
                        logger.info("historys:" + newHistorys);
                        user.setHistory(newHistorys.toString() + param);
                    }
                } else {
                    if (iscollection == -1) {
                        //不包含去除首部
                        StringBuilder newHistorys = new StringBuilder("");
                        for (int index = 1; index < historys.length; index++) {
                            newHistorys.append(historys[index] + ";");
                        }
                        user.setHistory(newHistorys.toString() + param);
                    } else {
                        //包含去旧
                        StringBuilder newHistorys = new StringBuilder("");
                        for (int index = 0; index < historys.length; index++) {
                            if (iscollection == index)
                                continue;
                            newHistorys.append(historys[index] + ";");
                        }
                        user.setHistory(newHistorys.toString() + param);
                    }
                }
            }
            userMapper.updateHistory(user);
            model.addAttribute("user", user);
        }

    }

    public int iscollection(String[] collections, int id) {
        int index = 0;
        for (index = 0; index < collections.length; index++) {
            if (collections[index].equals(String.valueOf(id))) {
                logger.info("check");
                return index;
            }
        }
        return -1;
    }
}
