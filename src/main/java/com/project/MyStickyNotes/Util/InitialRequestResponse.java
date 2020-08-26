package com.project.MyStickyNotes.Util;

import com.project.MyStickyNotes.Dto.DetailsDto;
import com.project.MyStickyNotes.Dto.RegisterDto;

import java.util.List;

public class InitialRequestResponse {
    int todaytasks;
    int pendingtasks;
    int totaltasks;
    int completedtasks;
    List<DetailsDto> tasksonloginday;
    int totalsize;

    public int getTodaytasks() {
        return todaytasks;
    }

    public void setTodaytasks(int todaytasks) {
        this.todaytasks = todaytasks;
    }

    public int getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(int totalsize) {
        this.totalsize = totalsize;
    }

    public int getPendingtasks() {
        return pendingtasks;
    }

    public void setPendingtasks(int pendingtasks) {
        this.pendingtasks = pendingtasks;
    }

    public int getTotaltasks() {
        return totaltasks;
    }

    public void setTotaltasks(int totaltasks) {
        this.totaltasks = totaltasks;
    }

    public int getCompletedtasks() {
        return completedtasks;
    }

    public void setCompletedtasks(int completedtasks) {
        this.completedtasks = completedtasks;
    }

    public List<DetailsDto> getTasksonloginday() {
        return tasksonloginday;
    }

    public void setTasksonloginday(List<DetailsDto> tasksonloginday) {
        this.tasksonloginday = tasksonloginday;
    }
}
