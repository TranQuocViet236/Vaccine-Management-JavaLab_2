package ListClass;

import java.io.Serializable;

public class Injection implements Serializable {
    public String id,FirstPlace,SecondPlace,FirstDate,SecondDate,studentID,vaccineID;
    public Injection(){}
    public Injection(String id,String FirstPlace,String FirstDate,String studentID,String vaccineID){
        this.id = id;
        this.FirstPlace = FirstPlace;
        this.FirstDate = FirstDate;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.SecondDate = null;
        this.SecondPlace = null;
    }
    public Injection(String id,String FirstPlace,String SecondPlace,String FirstDate
            ,String SecondDate,String studentID,String vaccineID){
        this.id = id;
        this.FirstPlace = FirstPlace;
        this.FirstDate = FirstDate;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.SecondDate = SecondDate;
        this.SecondPlace = SecondPlace;
    }
}
