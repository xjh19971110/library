package com.entity;

public class comment {
    private int cid;
    private int bid;
    private String content;
    private int uid;


    public comment() {
    }

    public comment(int cid, int bid, String content, int uid) {
        this.cid = cid;
        this.bid = bid;
        this.content = content;
        this.uid = uid;

    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


}
