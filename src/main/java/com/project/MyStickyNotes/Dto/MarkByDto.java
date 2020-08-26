package com.project.MyStickyNotes.Dto;

public class MarkByDto {
    String checkedStatus;
    int id[];

    public String getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(String checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    public int[] getId() {
        return id;
    }

    public void setId(int[] id) {
        this.id = id;
    }
}
