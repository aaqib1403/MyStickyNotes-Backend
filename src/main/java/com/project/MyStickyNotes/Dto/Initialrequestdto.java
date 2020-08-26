package com.project.MyStickyNotes.Dto;

public class Initialrequestdto {
    String username;
    int pagesize = 3;
    int pagenumber = 1;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(int pagenumber) {
        this.pagenumber = pagenumber;
    }
}
