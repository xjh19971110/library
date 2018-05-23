package com.dto;

public class AppointExecution {
    private int book_id;
    private int state;
    private String stateInfo;

    public AppointExecution() {
    }

    public AppointExecution(int book_id,AppointStateEnum stateEnum) {
        this.book_id = book_id;
        this.state=stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
    @Override
    public String toString() {
        return "AppointExecution [bookId=" +book_id + ", state=" + state + ", stateInfo=" + stateInfo+"]";
    }
}
