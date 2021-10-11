package VaccineManagement;

import ListClass.Injection;
import ListClass.Student;
import ListClass.Vaccine;

import java.io.*;
import java.util.*;

public class Function{
    List<Injection> injectionList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    List<Vaccine> vaccineList = new ArrayList<>();
    Validation validation = new Validation();

    public Function(){
        informationInjection();
        informationVaccine();
        informationStudent();
    }

    void informationInjection(){
        injectionList.clear();
        try{
            FileInputStream file =new FileInputStream("injection.dat");
            ObjectInputStream oStream =new ObjectInputStream(file);
            while (true){
                Injection injection=(Injection)oStream.readObject();
                if(injection==null) break;
                injectionList.add(injection);
            }System.out.println("Add injection Successfully");
            oStream.close();
            file.close();
        }catch (IOException | ClassNotFoundException e) {
        }
    }

    void informationStudent(){
        studentList.clear();
        try{
            FileInputStream file = new FileInputStream("student.dat");
            ObjectInputStream oStream = new ObjectInputStream(file);
            while (true){
                Student student = (Student)oStream.readObject();
                if(student==null) break;
                studentList.add(student);
            } System.out.println("Add student successfully");
            oStream.close();
            file.close();
        }catch (IOException | ClassNotFoundException e){
        }
    }

    void informationVaccine(){
        vaccineList.clear();
        try{
            FileInputStream file = new FileInputStream("vaccine.dat");
            ObjectInputStream oStream = new ObjectInputStream(file);
            while (true){
                Vaccine vaccine = (Vaccine)oStream.readObject();
                if(vaccine==null) break;
                vaccineList.add(vaccine);
            }System.out.println("Add vaccine successfully");
            oStream.close();
            file.close();
        }catch (IOException | ClassNotFoundException e){
        }
    }

    public static int CheckMenu() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean check = false;
        do{
            try {
                number = Integer.parseInt(sc.nextLine());
                if(number < 1 || number > 6) throw new Exception();
                check = false;
            }catch(Exception e){
                System.out.println("Invalid Input");
                System.out.println("Please Enter Again");
                check = true;
            }
        } while(check);
        return number;
    }
    //Show information
    void showListInjection(){
        listInjection();
        for(Injection injection: injectionList) displayInjection(injection);
    }
    public static void listInjection(){
        System.out.printf("%-20s%-25s%-25s%-25s%-25s%-25s%-25s\n", "Injection ID",
                "First Place", "First Date", "Second Place","Second Date","Student ID", "Vaccine ID");
        System.out.println("-".repeat(155));
    }
    public static void displayInjection(Injection injection){
        if(injection.SecondDate == null) System.out.printf("%-20s%-25s%-25s%-25s%-25s%-25s%-25s\n", injection.id,
                injection.FirstPlace,injection.FirstDate, "null", "null", injection.studentID, injection.vaccineID);
        else System.out.printf("%-20s%-25s%-25s%-25s%-25s%-25s%-25s\n", injection.id, injection.FirstPlace,
                injection.FirstDate, injection.SecondPlace, injection.SecondDate, injection.studentID, injection.vaccineID);
    }
    //Add information
    void addInformation(){
        String id="",firstPlace="",secondPlace="",firstDate="",secondDate="",studentID="",vaccineID="" ;
        boolean check = true;
        do {
            do {
                try {
                    id = validation.checkStr("Enter Injection ID: ").toUpperCase();
                    if (checkIDInj(id) > -1) throw new Exception("Injection ID is exist");
                    check = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    check = true;
                    System.out.println("Please Enter again");
                }
            } while (check);
            check = true;
            do {
                try {
                    studentID = validation.checkStr("Enter Student ID: ").toUpperCase();
                    if (checkIDStu(studentID) > -1) {
                        if (checkIDInjStu(studentID) < 0) check = false;
                        else throw new Exception("This Student has Injection!");
                    } else throw new Exception("This Student ID is not exist!");
                    check = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    check = true;
                    System.out.println("Please Enter again");
                }
            } while (check);
            check = true;
            do {
                try {
                    vaccineID = validation.checkStr("Enter Vaccine ID: ");
                    if (checkIDVac(vaccineID) > -1) check = false;
                    else throw new Exception("This Vaccine ID is not exist");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    check = true;
                    System.out.println("Please Enter again");
                }
            } while (check);
            firstPlace = validation.checkPlace("Enter First Place: ");
            firstDate = validation.checkDate("Enter First Date (dd/mm/yyyy): ");
            if (validation.isContinue("Do you want input second injection?(y/n)?: ") == 1) {
                secondPlace = validation.checkPlace("Enter Second Place: ");
                secondDate = validation.checkSecondDate(firstDate);
                injectionList.add(new Injection(id, firstPlace, secondPlace, firstDate, secondDate, studentID, vaccineID));
            } else injectionList.add(new Injection(id, firstPlace, firstDate, studentID, vaccineID));
            int iContinue = validation.isContinue();
            switch (iContinue){
                case 1: check=true; break;
                case 2: check=false; break;
            }
        }while (check);
    }

    int checkIDInj(String id){
        for(Injection injection:injectionList) if(injection.id.equals(id)) return injectionList.indexOf(injection);
        return -1;
    }

    int checkIDStu(String id){
        for(Student student:studentList) if(student.studentID.equals(id)) return studentList.indexOf(student);
        return -1;
    }

    int checkIDVac(String id){
        for(Vaccine vaccine:vaccineList) if(vaccine.vaccineID.equals(id)) return vaccineList.indexOf(vaccine);
        return -1;
    }

    int checkIDInjStu(String id){
        for(Injection injection:injectionList) if(injection.studentID.equals(id)) return injectionList.indexOf(injection);
        return -1;
    }
    //update
    void updateInformation(){
        int number = 0;
        while(true){
                String id = validation.checkStr("Enter injection ID: ").toUpperCase(Locale.ROOT);
                number = checkIDInj(id);
                if(number > -1){
                    if(injectionList.get(number).SecondDate == null) break;
                    else System.out.println("This Student has completed 2 injection");
                } else System.out.println("This Injection ID is not exist");
                if(validation.isContinue("Do you want to try update again (y/n)?: ") == 0) return;
        };
        Injection injection = injectionList.get(number);
        System.out.println("First Place: "+injection.FirstPlace);
        System.out.println("First Date: "+injection.FirstDate);
        injection.SecondPlace = validation.checkPlace("Enter Second Place: ");
        injection.SecondDate = validation.checkSecondDate(injection.FirstDate);
        if(injection.SecondDate == null) System.out.println("This update is fail");
        else System.out.println("This update is successfully");
    }
    //delete
    void deleteInformation(){
        int number=0;
        boolean check = true;
        do{
            try{
                String id = validation.checkStr("Enter injection ID: ").toUpperCase();
                number = checkIDInj(id);
                if (number>-1) check = false;
                else throw new Exception("This id is not exist");
            }catch (Exception e){
                System.out.println(e.getMessage());
                check = true;
                System.out.println("Please enter again: ");
            }
        }while (check);
        if(validation.isContinue("Do you want to delete (y/n)?: ")==1){
            injectionList.remove(number);
            System.out.println("Delete Successfully");
        } else System.out.println("Delete Fail");
    }
    //search
    void searchInformation(){
        boolean check = true;
        do {
            String id = validation.checkStr("Enter student id: ");
            int number = checkIDInjStu(id);
            if (number > -1) {
                listInjection();
                displayInjection(injectionList.get(number));
                System.out.println("Search Successfully");
            } else System.out.println("Search Fail, Cannot Find ID");
            int iContinue = validation.isContinue();
            switch (iContinue){
                case 1: check=true; break;
                case 0: check=false; break;
            }
        }while(check);
    }
    //save
    void saveInformation(){
        try{
            FileOutputStream file = new FileOutputStream("injection.dat");
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            for(Injection injection:injectionList) oStream.writeObject(injection);
            System.out.println("Save Successfully");
        }catch(IOException e){
            System.out.println("Error: "+e);
        }
    }

}












