package VaccineManagement;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Choice extends Vector<String> {
    public Choice() {
        super();
    }
    ArrayList<String> menu = new ArrayList<>();
    void addMenuItem(String option){menu.add(option);}
    int getUserChoice(){
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i< menu.size();i++){
            System.out.println(menu.get(i));
        }
        System.out.println("Enter Your Choice");
        int Choice = Function.CheckMenu();
        return Choice;
    }
}
