package com.mycompany.student_grade_management_system_gpa;

import java.util.ArrayList;
import java.util.List;
 
public class StudentLinkedList {
 
    private static class Node {
        Student data;
        Node prev, next;
        Node(Student s) { this.data = s; }
    }
 
    private Node head, tail;
    private int size;
 
    // Insert sorted by GPA ascending, StudentID ascending as tiebreaker
    public void insert(Student s) {
        if (s == null) {
            System.out.println("Error: student cannot be null.");
            return;
        }
 
        // Check for duplicate ID
        Node cur = head;
        while (cur != null) {
            if (cur.data.getStudentID() == s.getStudentID()) {
                System.out.println("Student with ID=" + s.getStudentID() + " already exists.");
                return;
            }
            cur = cur.next;
        }
 
        Node newNode = new Node(s);
 
        if (head == null) {
            head = tail = newNode;
            size++;
            return;
        }
 
        // Find insertion point
        cur = head;
        while (cur != null) {
            int cmp = Double.compare(s.getGPA(), cur.data.getGPA());
            if (cmp == 0) cmp = Integer.compare(s.getStudentID(), cur.data.getStudentID());
            if (cmp <= 0) break;
            cur = cur.next;
        }
 
        if (cur == null) {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        } else if (cur == head) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            newNode.prev = cur.prev;
            newNode.next = cur;
            cur.prev.next = newNode;
            cur.prev = newNode;
        }
        size++;
    }
 
    // Search by StudentID
    public Student search(int studentID) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.getStudentID() == studentID) return cur.data;
            cur = cur.next;
        }
        return null;
    }
 
    // Remove by StudentID
    public boolean remove(int studentID) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.getStudentID() == studentID) {
                if (cur.prev != null) cur.prev.next = cur.next;
                else                  head = cur.next;
                if (cur.next != null) cur.next.prev = cur.prev;
                else                  tail = cur.prev;
                size--;
                return true;
            }
            cur = cur.next;
        }
        System.out.println("Student with ID=" + studentID + " not found.");
        return false;
    }
 
    public int size() { return size; }
 
    // TOP10 - highest GPA first (traverse from tail)
    public void top10() {
        if (size == 0) { System.out.println("No students in the system."); return; }
        System.out.println("\n===== TOP 10 Students by GPA (Highest to Lowest) =====");
        Node cur = tail;
        int count = 0, rank = 1;
        while (cur != null && count < 10) {
            System.out.println(rank++ + ". " + cur.data);
            cur = cur.prev;
            count++;
        }
    }
 
    // TOP5 - top 5 by GPA, then sorted by StudentID descending
    public void top5() {
        if (size == 0) { System.out.println("No students in the system."); return; }
        List<Student> top = new ArrayList<>();
        Node cur = tail;
        int count = 0;
        while (cur != null && count < 5) {
            top.add(cur.data);
            cur = cur.prev;
            count++;
        }
        top.sort((a, b) -> Integer.compare(b.getStudentID(), a.getStudentID()));
        System.out.println("\n===== TOP 5 Students sorted by ID (Highest to Lowest) =====");
        for (int i = 0; i < top.size(); i++)
            System.out.println((i + 1) + ". " + top.get(i));
    }
 
    // Isolate all students with GPA < 2.0 into a new list
    public StudentLinkedList isolateLowGPA() {
        StudentLinkedList lowList = new StudentLinkedList();
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            if (cur.data.getGPA() < 2.0) {
                Student s = cur.data;
                remove(s.getStudentID());
                lowList.insert(s);
            }
            cur = next;
        }
        System.out.println("[Isolated] " + lowList.size() + " student(s) with GPA < 2.0 moved to a separate list.");
        return lowList;
    }
 
    // Display all students sorted by GPA ascending
    public void display() {
        if (size == 0) { System.out.println("List is empty."); return; }
        System.out.println("\n===== All Students (sorted by GPA ascending) =====");
        Node cur = head;
        int rank = 1;
        while (cur != null) {
            System.out.println(rank++ + ". " + cur.data);
            cur = cur.next;
        }
    }
}