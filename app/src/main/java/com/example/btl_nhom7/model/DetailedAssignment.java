package com.example.btl_nhom7.model;

public class DetailedAssignment {
    private String className;
    private String day;
    private String startTime;
    private String endTime;
    private String roomName;
    private int roomType; // "Lý thuyết" or "Thực hành"
    private String task;

    // Constructor, getters and setters
    public DetailedAssignment(String className, String day, String startTime, String endTime, String roomName, int roomType, String task) {
        this.className = className;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.roomName = roomName;
        this.roomType = roomType;
        this.task = task;
    }

    // Getters and setters
    public String getClassName() { return className; }
    public String getDay() { return day; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getRoomName() { return roomName; }
    public int getRoomType() { return roomType; }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
