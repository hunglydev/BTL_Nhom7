package com.example.btl_nhom7.model;

public class ClassStudent {
    private String idStudent;
    private String idClass;
    private int rating;
    private String className;


    public ClassStudent(String idStudent, String idClass, int rating, String className) {
        this.idStudent = idStudent;
        this.idClass = idClass;
        this.rating = rating;
        this.className = className;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
