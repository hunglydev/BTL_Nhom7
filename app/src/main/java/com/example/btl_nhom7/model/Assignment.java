package com.example.btl_nhom7.model;

import android.graphics.SweepGradient;

public class Assignment {
    private String idClass;
    private String day;
    private String startTime;
    private String endTime;
    private String idRoom;
    private String idTeacher;
    private String note;
    private int isRated;

    public Assignment(String idClass, String day, String startTime, String endTime, String idRoom, String idTeacher, String note, int isRated) {
        this.idClass = idClass;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idRoom = idRoom;
        this.idTeacher = idTeacher;
        this.note = note;
        this.isRated = isRated;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIsRated() {
        return isRated;
    }

    public void setIsRated(int isRated) {
        this.isRated = isRated;
    }
}
