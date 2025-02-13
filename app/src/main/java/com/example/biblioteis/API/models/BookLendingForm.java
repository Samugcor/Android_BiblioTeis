package com.example.biblioteis.API.models;

public class BookLendingForm {
    private int bookid;
    private int userid;

    public BookLendingForm() {
    }

    public BookLendingForm(int bookid, int userid) {
        this.bookid = bookid;
        this.userid = userid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
