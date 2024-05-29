package com.example.btl_nhom7.model;

public class Classes {
    String idClass;
    String name;
    String idTeacher;

    public Classes(String idClass, String name, String idTeacher) {
        this.idClass = idClass;
        this.name = name;
        this.idTeacher = idTeacher;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }
    public Classes(){

    }
}
