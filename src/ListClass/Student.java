package ListClass;

import java.io.Serializable;

public class Student implements Serializable {
    public String studentID,studentName;
    public Student(){}
    public Student(String studentID,String studentName){
        this.studentID= studentID;
        this.studentName = studentName;
    }
}
