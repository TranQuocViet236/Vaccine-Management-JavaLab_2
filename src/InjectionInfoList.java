import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class InjectionInfoList extends ArrayList<InjectionInfo>{
    Scanner sc = new Scanner(System.in);
    public void adddInjectionInfo(){
        boolean add = true;
        while (add == true){
            InjectionInfo inj = new InjectionInfo();
            inj.input(this);
            //add to the list
            if (this.add(inj) == true) {
                System.out.println("Added successfully!");
            } else {
            }
            System.out.println("Do you want to add more injection information (Y/N)?");
            add = Validation.checkInputYN();
        }
    }

    public void updateInjectionInfo() {
        if (this.isEmpty()){
            System.out.println("Empty list!...");
        }
//        for (InjectionInfo inj : this){
//            if(equals(inj)){
//                System.out.println("This id has already exist! ");
//                return;
//            }
//        }
        System.out.println("Enter updated ID: ");
        String updated_id = Validation.checkInputString();
        int index = this.indexOf(new InjectionInfo(updated_id));
        if (index < 0) {
            System.out.println("Not found!");
        } else {
                if (this.get(index).getFir_injection_date() != null && this.get(index).getSec_injection_date() != null){
                    System.out.println("Student has completed 2 injections!");
                }
                else {
                    if (this.get(index).getFir_injection_date() == null){
                        System.out.println("You have to completer input first injection date first!");
                        String fir_d = Validation.checkInputDate();
                        this.get(index).setFir_injection_date(fir_d);
                    }
//                    if (this.get(index).getFir_injection_place() == null){
//                        System.out.println("You have to completer input first injection place first!");
//                        String fir_p = Validation.checkInputString();
//                        this.get(index).setFir_injection_place(fir_p);
//                    }
                    System.out.println("Enter second injection place: ");
                    String sec_p = Validation.checkInputString();
                    this.get(index).setSec_injection_place(sec_p);
                    System.out.println("Enter second injection date: ");
                    String sec_d = Validation.checkSecondDate(this.get(index).getFir_injection_date());
                    this.get(index).setSec_injection_place(sec_d);
//                    System.out.println("Enter student ID:");
//                    String std_id = Validation.checkInputStudentID();
//                    this.get(index).setStudent_id(std_id);
//                    System.out.println("Enter vaccine ID:");
//                    String vc_id = Validation.checkInputVaccineID();
//                    this.get(index).setVaccine_id(vc_id);
                }
        }
       System.out.println(this.get(index));
    }
    public void deleteInjectionInfo() {
        if (this.size() == 0) {
            System.out.println("Empty injection list!");
            return;
        }
        System.out.println("Enter removed ID: ");
        String removed_id = Validation.checkInputString();
        int index = this.indexOf(new InjectionInfo(removed_id));
        if (index < 0) {
            System.out.println("Not found!");
        } else {
            System.out.println("Are you sure you want to delete? ");
            boolean message = Validation.checkInputYN();
            if (message == true){
                this.remove(index);
                System.out.println("Injection infomation " + removed_id + " was removed!");
                System.out.println("Removed successfully!...");
            }
            else{
                System.out.println("Returning...");
                return;
            }
        }
    }
    public void searchByStudentID() {
        if (this.size() == 0) {
            System.out.println("Empty injection list!");
            return;
        }
        boolean search = true;
        while(search){
            System.out.println("Enter the searched student ID: ");
            String search_ID = Validation.checkInputStudentID();
            int count1 = 0;
            int count2 = 0;
            for (InjectionInfo inj : this){
                if(inj.getStudent_id() != null){
                    if(search_ID.equals(inj.getStudent_id()) || inj.getStudent_id().contains(search_ID)){
                        System.out.println(inj);
                        break;
                    }else {
                        System.out.println("Not found!");
                        break;
                    }
                }
            }

//            for (InjectionInfo inj : this){
//                if(inj.getStudent_id() != null){
//                    if(!search_ID.equals(inj.getStudent_id().toLowerCase()) || !inj.getStudent_id().toLowerCase().contains(search_ID)){
//                        System.out.println("Not found");
//                        break;
//                    }
//                }
//            }
            System.out.println("Do you want to search more student's injection (Y/N)?");
            search = Validation.checkInputYN();
        }

    }
    public void printAll(){
        if (this.size() == 0) {
            System.out.println("Empty injection list!");
            return;
        }

        for (InjectionInfo inj : this){
                System.out.println(inj);
        }
    }

    public void saveInformation(){
        try{
            FileOutputStream file = new FileOutputStream("injection.dat");
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            for (InjectionInfo inj : this){
                System.out.println("Save successfully!");
            }
        }catch (IOException e){
            System.out.println("Error: "+ e);
        }
    }
    public void informationInjection(){
        this.clear();
        try{
            FileInputStream file = null;
            try {
                file = new FileInputStream("injection.dat");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ObjectInputStream oStream = null;
            try {
                oStream = new ObjectInputStream(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true){
                InjectionInfo inj = null;
                try {
                    inj = (InjectionInfo) oStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(inj == null) break;
                this.add(inj);
            }System.out.println("Add injection Successfully!");
            try {
                oStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("Some things went wrong!...");
        }
    }
    public static void readFile(ArrayList<InjectionInfo> inj, String fName) {
        try {
            File f = new File(fName);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String detail;
            while ((detail = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(detail, ",");
                String injection_id = stk.nextToken();
                String fir_place = stk.nextToken();
                String fir_date = stk.nextToken();
                String sec_place = stk.nextToken();
                String sec_date = stk.nextToken();
                String student_id = stk.nextToken();
                String vaccineID = stk.nextToken();
                InjectionInfo dt = new InjectionInfo(injection_id, fir_place, fir_date, sec_place, sec_date, student_id, vaccineID);
                inj.add(dt);
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("This is new file");
        }
    }
    public void saveToFile(ArrayList<InjectionInfo> injList, String fName){
        try{
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i<injList.size();i++){
                pw.println(injList.get(i).getInjection_id()+ ", " + injList.get(i).getFir_injection_place()+", " +
                        injList.get(i).getFir_injection_date() + ", " + injList.get(i).getSec_injection_place() + ", " +
                        injList.get(i).getSec_injection_date() + ", " + injList.get(i).getStudent_id()+ ", " + injList.get(i).getVaccine_id());
            }
            fw.close();
            pw.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
