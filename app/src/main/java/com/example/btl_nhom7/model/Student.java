package com.example.btl_nhom7.model;

public class Student {
    String idStudent;
    String password;
    String name;
    String idClass;

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public Student(String idStudent, String password, String name, String idClass) {
        this.idStudent = idStudent;
        this.password = password;
        this.name = name;
        this.idClass = idClass;
    }
    public Student(){

    }
}
