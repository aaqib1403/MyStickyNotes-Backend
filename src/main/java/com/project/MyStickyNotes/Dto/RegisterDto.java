package com.project.MyStickyNotes.Dto;

import javax.persistence.OneToMany;
import java.util.List;

public class RegisterDto {

    String firstname;

    String Lastname;
    
    String username;
    String password;


    List<DetailsDto> detailsList;

    public List<DetailsDto> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<DetailsDto> detailsList) {
        this.detailsList = detailsList;
    }

    public String getFirstname() {
        return firstname;
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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }


}
