package com.project.MyStickyNotes.Util;

import java.util.Date;
import java.util.List;

public class DetailsFormatter {

    Date createdon;
    List<String> taskdetails;

    public Date getCreatedon() {
        return createdon;
    }

    public void setCreatedon(Date createdon) {
        this.createdon = createdon;
    }

    public List<String> getTaskdetails() {
        return taskdetails;
    }

    public void setTaskdetails(List<String> taskdetails) {
        this.taskdetails = taskdetails;
    }
}
