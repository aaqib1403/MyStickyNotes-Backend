package com.project.MyStickyNotes.Dto;

public class EditDto {

    String taskdetails;
    int id;
    String status;

    public String getTaskdetails() {
        return taskdetails;
    }

    public void setTaskdetails(String taskdetails) {
        this.taskdetails = taskdetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
