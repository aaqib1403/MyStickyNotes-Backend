package com.project.MyStickyNotes.Entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Register_TBL")
@EntityListeners(AuditingEntityListener.class)
public class RegisterEntity {

    @Id
    @Column(length = 64)
    String username;
    String firstname;
    String Lastname;
    String password;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
            @CreatedDate
    Date createdon;
    @OneToMany(mappedBy = "registerEntity")
    @ElementCollection(fetch = FetchType.LAZY)
    List<DetailsEntity> detailsEntityList;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public List<DetailsEntity> getDetailsEntityList() {
        return detailsEntityList;
    }

    public void setDetailsEntityList(List<DetailsEntity> detailsEntityList) {
        this.detailsEntityList = detailsEntityList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

}
