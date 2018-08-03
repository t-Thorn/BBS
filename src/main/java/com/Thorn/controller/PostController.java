package com.Thorn.controller;

import com.Thorn.dao.postMapper;
import com.Thorn.dao.replyMapper;
import com.Thorn.dao.userMapper;
import com.Thorn.model.post;
import com.Thorn.model.reply;
import com.Thorn.model.userWithBLOBs;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

//@todo:新增回复和楼中楼
//todo:删除回复
@Controller
@SessionAttributes("userSession")
public class PostController {
    @Autowired
    postMapper postmapper;
    @Autowired
    userMapper userMapper;
    @Autowired
    replyMapper replyMapper;

    private Logger logger = Logger.getLogger(this.getClass());

    @GetMapping("/Post/new")
    public String jumptoNewPost() {
        return "/Login/write";
    }

    @PostMapping("/Post/newpost")
    public String newpost(@ModelAttribute("newpost") post post,
                          @ModelAttribute("userSession") userWithBLOBs user,
                          @RequestParam String content, Model model) {
        logger.info(post.getTitle());
        logger.info(post.getType());
        logger.info(content);

        post.setUsername(user.getUsername());
        post.setPosttime(new Date());
        post.setLastposttime(new Date());
        postmapper.insert(post);

        int newid = postmapper.findNewPostID();
        logger.info(postmapper.findNewPostID());

        user.setMyPostnum(user.getMyPostnum() + 1);
        userMapper.updateMyPostnum(user);

        reply reply = new reply();
        reply.setPostid(newid);
        reply.setContent(content);
        reply.setFloor(0);
        reply.setReplyer(user.getUsername());
        reply.setReplytime(new Date());

        replyMapper.insert(reply);
        model.addAttribute("param", newid);


        return "redirect:/BBS/post";
    }

    @PostMapping("/Post/newreply")
    public String newReply(@ModelAttribute("newreply") reply reply) {
        return "";
    }

    @PostMapping(value = "/Post/iscollection")
    public @ResponseBody
    String collection(int postid, int method, @ModelAttribute
            ("userSession") userWithBLOBs user, Model model) {
        logger.info("postid:" + postid);
        String[] collections = new String[]{""};
        if (user.getCollections() != null && !user.getCollections().equals("")) {
            collections = user.getCollections().split(";");
        }
        //
        if (method == 0) {
            //查询是否收藏
            if (!collections[0].equals("")) {
                for (String collection : collections) {
                    if (collection.equals(String.valueOf(postid))) {
                        logger.info("已收藏");
                        return "1";
                    }
                }
                logger.info("未收藏");
                return "0";
            }
            logger.info("未收藏1");
            return "0";
        } else {
            //收藏/取消收藏
            String newcollections = "";
            for (int index = 0; index < collections.length; index++) {
                if (collections[index].equals(String.valueOf(postid))) {
                    //取消收藏
                    newcollections = setcollection(collections, index, "");
                    logger.info("newcollections:" + newcollections);
                    user.setCollections(newcollections);
                    userMapper.updateCollections(user);
                    model.addAttribute(user);
                    return "0";
                }
            }
            //收藏
            newcollections = setcollection(collections, -1, String.valueOf(postid));
            logger.info("newcollections:" + newcollections);
            user.setCollections(newcollections);
            userMapper.updateCollections(user);
            model.addAttribute(user);
            return "1";
        }
    }

    public String setcollection(String[] collections, int index, String newcollection) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < collections.length; i++) {
            if (i == index)
                continue;
            if (!collections[0].equals(""))
                stringBuilder.append(collections[i] + ";");
        }
        if (!newcollection.equals(""))
            stringBuilder.append(newcollection);
        return stringBuilder.toString();
    }
}
