package com.project.MyStickyNotes.Dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsernameAndDates {
    String username;
    String fromDate;
    String toDate;
    String status;
    int pagesize;
    int pagenumber;

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

    public UsernameAndDates() {
    }

    public UsernameAndDates(String status) {
        this.status = status;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {

        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
