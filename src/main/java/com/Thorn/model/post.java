package com.Thorn.model;

import java.util.Date;

public class post {
    private Integer id;

    private String content;

    private Date dob;

    private Integer blogId;

    public post(Integer id, String content, Date dob, Integer blogId) {
        this.id = id;
        this.content = content;
        this.dob = dob;
        this.blogId = blogId;
    }

    public post() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}