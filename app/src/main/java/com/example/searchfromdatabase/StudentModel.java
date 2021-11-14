package com.example.searchfromdatabase;

public class StudentModel {
    private String regNo;
    private String fullName;

    public StudentModel(String regNo, String fullName) {
        this.regNo = regNo;
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "regNo='" + regNo + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
