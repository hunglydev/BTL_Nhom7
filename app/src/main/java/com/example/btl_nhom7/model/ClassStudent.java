package com.example.btl_nhom7.model;

public class ClassStudent {
    private String idStudent;
    private String idClass;


    public ClassStudent(String idStudent, String idClass) {
        this.idStudent = idStudent;
        this.idClass = idClass;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public ClassStudent(){

    }
}
