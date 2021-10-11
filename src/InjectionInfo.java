import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class InjectionInfo{
    String injection_id ;
    String fir_injection_place;
    String fir_injection_date;
    String sec_injection_place;
    String sec_injection_date;
    String student_id;
    String vaccine_id;

    public InjectionInfo(){

    }

    public InjectionInfo(String injection_id) {
        this.injection_id = injection_id;
    }

    public InjectionInfo(String injection_id, String fir_injection_place, String sec_injection_place,
                         String fir_injection_date, String sec_injection_date, String student_id, String vaccine_id) {
        this.injection_id = injection_id;
        this.fir_injection_place = fir_injection_place;
        this.sec_injection_place = sec_injection_place;
        this.fir_injection_date = fir_injection_date;
        this.sec_injection_date = sec_injection_date;
        this.student_id = student_id;
        this.vaccine_id = vaccine_id;
    }

    public String getInjection_id() {
        return injection_id;
    }

    public void setInjection_id(String injection_id) {
        this.injection_id = injection_id;
    }

    public String getFir_injection_place() {
        return fir_injection_place;
    }

    public void setFir_injection_place(String fir_injection_place) {
        this.fir_injection_place = fir_injection_place;
    }

    public String getSec_injection_place() {
        return sec_injection_place;
    }

    public void setSec_injection_place(String sec_injection_place) {
        this.sec_injection_place = sec_injection_place;
    }

    public String getFir_injection_date() {
        return fir_injection_date;
    }

    public void setFir_injection_date(String fir_injection_date) {
        this.fir_injection_date = fir_injection_date;
    }

    public String getSec_injection_date() {
        return sec_injection_date;
    }

    public void setSec_injection_date(String sec_injection_date) {
        this.sec_injection_date = sec_injection_date;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getVaccine_id() {
        return vaccine_id;
    }

    public void setVaccine_id(String vaccine_id) {
        this.vaccine_id = vaccine_id;
    }

    @Override
    public String toString(){
        return this.getInjection_id() + ", " + this.getFir_injection_place()+ ", "  + this.getFir_injection_date() + ", "+ this.getSec_injection_place() + ", " + this.getSec_injection_date() +  ", " + this.getStudent_id() + ", " + this.getVaccine_id();
    }
    public void input(ArrayList<InjectionInfo> injectionList){
        Menu mnu1 = new Menu();

        mnu1.add("_____________INFORMATION____________");
        mnu1.add("1.Please enter injection ID: ");
        mnu1.add("2.Please enter first injection place: ");
        mnu1.add("3.Please enter first injection date:");
        mnu1.add("4.Please enter second injection place: ");
        mnu1.add("5.Please enter second injection date: ");
        mnu1.add("6.Please enter student ID: ");
        mnu1.add("7.Please enter vaccine ID: ");
        mnu1.add(" Any number keys difference 1-7 to back main MENU!");
        int userchoice;
        do{
//            System.out.println("What do you choose to fill?");
            userchoice = mnu1.getUserChoice();
            switch (userchoice){
                case 1:
                {
                    System.out.print("Injection_id: ");
                    injection_id = Validation.checkInputString();
                    for (InjectionInfo inj : injectionList){
                        if(equals(inj)){
                            System.out.println("This id has already exist! ");
                            return;
                        }
                    }
                    break;
                }
                case 2:
                {
                    System.out.print("First injection place: ");
                    fir_injection_place = Validation.checkInputString();
                    break;
                }
                case 3:{
                    System.out.print("First injection date: ");
                    fir_injection_date = Validation.checkInputDate();
                    break;
                }
                case 4:{
                    System.out.print("Second injection place: ");
                    sec_injection_place = Validation.checkInputString();
                    break;
                }
                case 5:
                {
                    System.out.print("Second injection date: ");
                    sec_injection_date = Validation.checkSecondDate(fir_injection_date);
                    break;
                }
                case 6:{
                    System.out.print("Student ID: ");
                    student_id = Validation.checkInputStudentID();
                    break;
                }
                case 7:
                {
                    System.out.print("Vaccine ID: ");
                    vaccine_id = Validation.checkInputVaccineID();
                    break;
                }
                default:break;
            }
        }while (userchoice >0 && userchoice < 8);


    }
    @Override
    public  boolean equals(Object obj){
        return this.injection_id.equals(((InjectionInfo)obj).injection_id);
    }

}
