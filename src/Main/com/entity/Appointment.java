package com.entity;

import java.util.Date;

public class Appointment {
    private int book_id;
    private int student_id;
    private Date appointTime;
    private book book1;
    public Appointment(){

    }

    public Appointment(int book_id, int student_id, Date appointTime, book book1) {
        this.book_id = book_id;
        this.student_id = student_id;
        this.appointTime = appointTime;
        this.book1 = book1;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Date getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Date appointTime) {
        this.appointTime = appointTime;
    }

    public book getBook1() {
        return book1;
    }

    public void setBook1(book book1) {
        this.book1 = book1;
    }
    @Override
    public String toString(){
        return "Appointment [bookId=" +book_id+ ", studentId=" +student_id + ", appointTime=" + appointTime + ", book="
                + book1 + "]";
    }
}
