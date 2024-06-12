package com.example.btl_nhom7.model;

public class Room {
    private String idRoom;
    private String name;
    private String device;
    private String task;
    private int method;

    public Room(String idRoom, String name, String device, String task, int method) {
        this.idRoom = idRoom;
        this.name = name;
        this.device = device;
        this.task = task;
        this.method = method;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }
    public Room(){

    }
}
