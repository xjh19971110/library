package com.entity;

public class book {
    private int book_id;
    private String name;
    private String introd;
    private int number;
    private int book_like;
    private int book_scan;
    private int book_comment;
  public book(){
      ;
  }
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntrod() {
        return introd;
    }

    public void setIntrod(String introd) {
        this.introd = introd;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBook_like() {
        return book_like;
    }

    public void setBook_like(int book_like) {
        this.book_like = book_like;
    }

    public int getBook_scan() {
        return book_scan;
    }

    public void setBook_scan(int book_scan) {
        this.book_scan = book_scan;
    }

    public int getBook_comment() {
        return book_comment;
    }

    public void setBook_comment(int book_comment) {
        this.book_comment = book_comment;
    }
}
