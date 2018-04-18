/**********************
 Hambartzum Gamburian
 HashTable.java
 **********************/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HashTable {

    private int size;
    private int capacity;
    private int incrementCapacity;
    private Student[] students;
    private Scanner input;

    public HashTable() {
        incrementCapacity = 100;
        capacity = incrementCapacity;
        size = 0;
        students = new Student[capacity];
    }

    public HashTable(int inCapacity) {
        incrementCapacity = inCapacity;
        capacity = incrementCapacity;
        size = 0;
        students = new Student[capacity];
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isFull() {
        return (size == capacity);
    }

    public void showAll() {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                System.out.println("Array Index " + i + ": " + students[i] + "\t");
            }
        }
    }

    private void incrementCapacity() {
        Student[] temp = new Student[capacity + incrementCapacity];
        for (int i = 0; i < size; i++) {
            temp[i] = students[i];
        }
        capacity += incrementCapacity;
        students = temp;
    }

    public void readInitialFromFile() {
        try {
            input = new Scanner(new File("students.txt"));

            while (input.hasNextLine()) {
                String line_str = input.nextLine();
                String[] studentInfo = line_str.split(" ");

                Student theStudent = new Student(studentInfo[0], studentInfo[1] + " " + studentInfo[2]);
                addStudent(theStudent);
            }
        }

        catch(FileNotFoundException e) {
            System.out.println("The \"students.txt\" file is either corrupt or is not found.");
            return;
        }
    }

    public void addStudent(Student s) {
        if (isFull()) {
            incrementCapacity();
        }

        int index = hashFunction(Integer.parseInt(s.getID()), 100);

        if (students[index] == null) {
            students[index] = s;
        }
        else {
            for (int i = index; ; i = hashFunction(i + 1, students.length)) {
                if (students[i] == null) {
                    students[i] = s;
                    break;
                }
            }
        }
        size++;
    }

    public String getStudentName(String ID) {
        String name = "";

        int index = hashFunction(Integer.parseInt(ID), 100);
        int numRan = 0;
        if (students[index] == null) {
            name = "";
        }

        else if (students[index].getID().equals(ID)) {
            name = students[index].getName();
        }

        else {
            for (int i = index; ; i = hashFunction(i + 1, students.length)) {
                if (numRan == students.length) {
                    break;
                }

                else {
                    if (students[i] != null) {
                        if (students[i].getID().equals(ID)) {
                            name = students[i].getName();
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }
                numRan++;
            }
        }
        return name;
    }

    public int hashFunction(int i, int j) {
        return (i % j);
    }

}