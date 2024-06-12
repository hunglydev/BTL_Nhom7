package com.example.btl_nhom7.model;

import android.graphics.SweepGradient;

public class Assignment {
    private String idClass;
    private String startTime;
    private String endTime;
    private String idRoom;
    private String idTeacher;
    private int status;

    public Assignment(String idClass, String startTime, String endTime, String idRoom, String idTeacher, int status) {
        this.idClass = idClass;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idRoom = idRoom;
        this.idTeacher = idTeacher;
        this.status = status;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
