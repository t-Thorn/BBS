package com.Thorn.util;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.log4j.Logger;

public class PostFile {
   /* private File postfile;
    private String content = "";
    @Autowired
    //private List<postDetail> postDetail;
    //private postDetail temppost;
    private int floor=0;
    private int flag = 0;//1=内容状态,0=配置文件状态
    private static Logger logger = Logger.getLogger(PostFile.class);

    public PostFile(String filename) {
        postfile = new File(filename);
    }

    private void load() {
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(postfile)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            line = br.readLine();
            while (line != null) {
                fetch(line);
                line = br.readLine(); // 一次读入一行数据
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fetch(String line) {
        System.out.println(line);
        if (flag == 1) {
            if (!line.equals("[/content]"))
                content += line;
            else
            {
                flag=0;
                temppost.setContent(content);
                postDetail.add(temppost);
                floor++;
            }
        }else
        {
            if (line.equals("[content]"))
                flag = 1;
            else
            {
                if(line.contains("[POSTID="))
                {
                    int postid=Integer.parseInt(line.substring(line.indexOf("=")+1),line.indexOf
                            ("]"));
                    temppost=new postDetail();
                    temppost.setPostid(postid);
                    temppost.setFloor(floor);
                    logger.info("新建一条楼层");
                }
            }
        }
    }

    public static void main(String[] args) {
        logger.info("新建一条楼层");
      //  PostFile postFile = new PostFile("E:\\project\\post\\1.txt");
        //postFile.load();
    }*/
}
