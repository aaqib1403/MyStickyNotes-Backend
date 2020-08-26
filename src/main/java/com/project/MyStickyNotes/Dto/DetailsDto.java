package com.project.MyStickyNotes.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DetailsDto {
    int id;
    Date createdon;
    String taskdetails;

    String status = "Pending";
    RegisterDto registerDto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //    public RegisterDto getRegisterDto() {
//		return registerDto;
//	}
//
    public void setRegisterDto(RegisterDto registerDto) {
        this.registerDto = registerDto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public String getTaskdetails() {
        return taskdetails;
    }

    public void setTaskdetails(String taskdetails) {
        this.taskdetails = taskdetails;
    }
}
