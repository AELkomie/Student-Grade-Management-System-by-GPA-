package com.mycompany.student_grade_management_system_gpa;

import java.util.Scanner;
 
public class Student_Grade_Management_System_GPA {
 
    private static Scanner sc = new Scanner(System.in);
    private static StudentLinkedList list = new StudentLinkedList();
    private static StudentLinkedList lowGPAList = null;
 
    public static void main(String[] args) {
        preloadSampleData();
 
        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = readInt("Choose: ");
            switch (choice) {
                case 1:
                    insertStudent();
                    break;
                case 2:
                    searchStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    System.out.println("Size: " + list.size());
                    break;
                case 5:
                    list.display();
                    break;
                case 6:
                    list.top10();
                    break;
                case 7:
                    list.top5();
                    break;
                case 8:
                    lowGPAList = list.isolateLowGPA();
                    break;
                case 9:
                    showLowGPA();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        sc.close();
    }
 
    private static void printMenu() {
        System.out.println("\n--- Student Grade Management System ---");
        System.out.println("1. Insert student");
        System.out.println("2. Search by ID");
        System.out.println("3. Remove by ID");
        System.out.println("4. Size");
        System.out.println("5. Display all");
        System.out.println("6. TOP10 (by GPA, highest to lowest)");
        System.out.println("7. TOP5  (by ID,  highest to lowest)");
        System.out.println("8. Isolate students with GPA < 2.0");
        System.out.println("9. Show isolated students");
        System.out.println("0. Exit");
    }
 
    private static void insertStudent() {
        int id = readInt("Student ID: ");
        System.out.print("Student Name: ");
        String name = sc.nextLine().trim();
        double gpa = readDouble("GPA (0.0 - 4.0): ");
        if (gpa < 0.0 || gpa > 4.0) {
            System.out.println("Invalid GPA. Must be between 0.0 and 4.0.");
            return;
        }
        list.insert(new Student(id, name, gpa));
        System.out.println("Student inserted successfully.");
    }
 
    private static void searchStudent() {
        int id = readInt("Enter Student ID: ");
        Student s = list.search(id);
        if (s == null) System.out.println("Student not found.");
        else           System.out.println("Found: " + s);
    }
 
    private static void removeStudent() {
        int id = readInt("Enter Student ID: ");
        boolean removed = list.remove(id);
        if (removed) System.out.println("Student removed successfully.");
    }
 
    private static void showLowGPA() {
        if (lowGPAList == null || lowGPAList.size() == 0) {
            System.out.println("No isolated students. Run option 8 first.");
            return;
        }
        lowGPAList.display();
    }
 
    private static void preloadSampleData() {
        list.insert(new Student(1001, "Ali Hassan",    3.85));
        list.insert(new Student(1002, "Nour Ahmed",    3.60));
        list.insert(new Student(1003, "Mariam Khalil", 2.90));
        list.insert(new Student(1004, "Omar Youssef",  1.75));
        list.insert(new Student(1005, "Hana Ibrahim",  3.40));
        list.insert(new Student(1006, "Tarek Mostafa", 1.50));
        list.insert(new Student(1007, "Salma Adel",    3.95));
        list.insert(new Student(1008, "Karim Nasser",  2.10));
        list.insert(new Student(1009, "Dina Farouk",   3.75));
        list.insert(new Student(1010, "Youssef Samir", 0.90));
        list.insert(new Student(1011, "Rania Waheed",  3.20));
        list.insert(new Student(1012, "Ahmed Zaki",    2.55));
        list.insert(new Student(1013, "Laila Mansour", 1.30));
        list.insert(new Student(1014, "Mahmoud Fathi", 3.10));
        list.insert(new Student(1015, "Sara Gamal",    3.50));
        System.out.println("15 sample students loaded.\n");
    }
 
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
 
    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}