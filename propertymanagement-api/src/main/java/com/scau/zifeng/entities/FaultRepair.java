package com.scau.zifeng.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FaultRepair {
    private Long id;

    private String contents;

    @JsonFormat( pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    private Long originator;

    private Long deal;

    private String status;

    private String feedback;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOriginator() {
        return originator;
    }

    public void setOriginator(Long originator) {
        this.originator = originator;
    }

    public Long getDeal() {
        return deal;
    }

    public void setDeal(Long deal) {
        this.deal = deal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback == null ? null : feedback.trim();
    }
}