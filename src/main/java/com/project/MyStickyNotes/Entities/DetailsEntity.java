package com.project.MyStickyNotes.Entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Details_TBL")
public class DetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date createdon;
    String taskdetails;
    String status;
    @ManyToOne
    @JoinColumn(name = "username")
    RegisterEntity registerEntity;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public RegisterEntity getRegisterEntity() {
        return registerEntity;
    }

    public void setRegisterEntity(RegisterEntity registerEntity) {
        this.registerEntity = registerEntity;
    }


}
