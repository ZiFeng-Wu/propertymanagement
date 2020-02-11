package com.scau.zifeng.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Complaint {
    private Long id;

    private Long uId;

    private String contents;

    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}