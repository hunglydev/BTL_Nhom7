package com.example.btl_nhom7.model;

import androidx.annotation.NonNull;

public class Student {
    private String idStudent;
    private String password;
    private String name;


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


    public Student(String idStudent, String password, String name) {
        this.idStudent = idStudent;
        this.password = password;
        this.name = name;

    }
    public Student(){

    }

    @NonNull
    @Override
    public String toString() {
        return this.idStudent + " - " + this.name;
    }
}
