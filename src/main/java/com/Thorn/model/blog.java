package com.Thorn.model;

import java.util.Date;

public class blog {
    private Integer id;

    private String title;

    private String content;

    private Date dob;

    private String state;

    private Integer authorId;
    author author;

    public com.Thorn.model.author getAuthor() {
        return author;
    }

    public void setAuthor(com.Thorn.model.author author) {
        this.author = author;
    }

    public blog(Integer id, String title, String content, Date dob, String state, Integer authorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dob = dob;
        this.state = state;
        this.authorId = authorId;
    }

    public blog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}