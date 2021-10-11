package ListClass;

import java.io.Serializable;

public class Vaccine implements Serializable {
    public String vaccineID, vaccineName;
    public Vaccine(){}
    public Vaccine(String vaccineID,String vaccineName){
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
    }
}
