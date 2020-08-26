package com.project.MyStickyNotes.Util;

import java.util.List;

public class DataResponse<T>{
    boolean success;

    List<T> data;

    int totalsize;

    public int getTotalsize() {
        return totalsize;
    }

    public void setTotalsize(int totalsize) {
        this.totalsize = totalsize;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
