package VaccineManagement;

import ListClass.Student;
import ListClass.Vaccine;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Write {
    public static void main(String[] args) {
        WriteStudent();
        WriteVaccine();
    }

    public static void WriteStudent() {
        try {
            String fileName = "student.dat";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            List<Student> list = new ArrayList<>();
            list.add(new Student("SE1501", "Nguyen Van A"));
            list.add(new Student("SE1502", "Nguyen Van B"));
            list.add(new Student("SE1503", "Nguyen Van C"));
            list.add(new Student("SE1504", "Nguyen Van D"));
            list.add(new Student("SE1505", "Nguyen Van E"));
            for (Student st : list) oStream.writeObject(st);
            oStream.close();
            file.close();
            System.out.println("Add Student Successfully");
        } catch (Exception e) {
        }
    }
    public static void WriteVaccine() {
        try {
            String fileName = "vaccine.dat";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            List<Vaccine> list = new ArrayList<>();
            list.add(new Vaccine("Covid-V001", "AstraZeneca"));
            list.add(new Vaccine("Covid-V002", "SPUTNIK V"));
            list.add(new Vaccine("Covid-V003", "Vero Cell"));
            list.add(new Vaccine("Covid-V004", "Pfizer"));
            list.add(new Vaccine("Covid-V005", "Moderna"));
            for (Object vc : list) oStream.writeObject(vc);
            oStream.close();
            file.close();
            System.out.println("Add Vaccine Successfully");
        } catch (Exception e) {
        }
    }

}