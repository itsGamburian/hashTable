/**********************
 Hambartzum Gamburian
 Student.java
 **********************/

public class Student {

    private String ID;
    private String name;

    public Student(String newID, String newName) {
        ID = newID;
        name = newName;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return ID + " " + name;
    }

}