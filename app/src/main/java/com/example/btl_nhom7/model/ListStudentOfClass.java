package com.example.btl_nhom7.model;

public class ListStudentOfClass {
    private String idStudent;
    private String idClass;
    private int rating;

    public ListStudentOfClass(String idStudent, String idClass, int rating) {
        this.idStudent = idStudent;
        this.idClass = idClass;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    public ListStudentOfClass(){

    }
}
