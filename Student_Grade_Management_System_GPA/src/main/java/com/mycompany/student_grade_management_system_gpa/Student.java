package com.mycompany.student_grade_management_system_gpa;

public class Student {
    private int studentID;
    private String studentName;
    private double GPA;
 
    public Student(int studentID, String studentName, double GPA) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.GPA = GPA;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getGPA() {
        return GPA;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }
 
    @Override
    public String toString() {
        return String.format("ID: %d | Name: %-20s | GPA: %.2f", studentID, studentName, GPA);
    }
}