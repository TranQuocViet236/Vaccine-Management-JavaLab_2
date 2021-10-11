import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Validation {
    private final static Scanner sc = new Scanner(System.in);
    private final static String student_id_valid = "^SE[0-9]{6}$";
    private final static String vaccine_id_valid = "^Covid-V[0-9]{3}$";
    public static String checkInputString() {
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("No empty input! ");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    public static String checkSecondDate(String date1) {
//        Date currentDay = new Date();
        while (true) {
            String date = sc.nextLine();
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
//                Date parseExpiredDay = sdf.parse(date);
                Date firstDate = sdf.parse(date1);
                Date secondDate = sdf.parse(date);
                long diff = secondDate.getTime() - firstDate.getTime();
                TimeUnit time = TimeUnit.DAYS;
                long difference = time.convert(diff, TimeUnit.MILLISECONDS);
//                System.out.println(difference);
                if (!(difference >= 28 && difference <=74)) {
                    throw new IllegalArgumentException();
                }
                return date;
            }catch (IllegalArgumentException er){
                System.err.println("Warning: The first injection date is: " + date1);
                System.err.println("Distance day of 2 injection days must between 28 and 74 days!");
            } catch (ParseException e) {
                System.err.println("Warning: The first injection date is: " + date1);
                System.err.println("Something went wrong!");
            }
        }
    }


    public static ArrayList<Object> getDatFile(String fileName) throws IOException {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Object> list = new ArrayList<Object>();
        Object obj = null;
        try {
            fis = new FileInputStream("./" + fileName);
            ois = new ObjectInputStream(fis);
            while (true) {
                try{
                    obj = ois.readObject();
                    list.add(obj);
                }catch(EOFException e){
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("The specified file could not be found" + fileName);
            System.exit(-1);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(ois!=null){
                ois.close();
            }
            if(fis!=null){
                fis.close();
            }
        }
        return list;
    }
    public static String checkInputStudentID() {
        while (true) {
            String result = checkInputString();
            //check user input phone valid
            if (result.matches(student_id_valid)) {
                return result;
            } else {
                System.err.println("Please enter Student ID with correct format!");
                System.out.print("Enter again: ");
            }
        }
    }

    public static String checkInputVaccineID() {
        while (true) {
            String result = checkInputString();
            //check user input phone valid
            if (result.matches(vaccine_id_valid)) {
                return result;
            } else {
                System.err.println("Please enter vaccine ID with correct format!");
                System.out.print("Enter again: ");
            }
        }
    }
    public static int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(sc.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Input interger!");
                System.out.print("Enter again: ");
            }
        }
    }
    public static boolean checkInputYN() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }
    public static double checkInputDouble() {
        while (true) {
            try {
                double result = Double.parseDouble(sc.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Input double!");
                System.out.println("Enter again: ");
            }
        }
    }

    public static double checkInputPositiveDouble() {
        while (true) {
            try {
                double result = Double.parseDouble(sc.nextLine());
                if (result < 0) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Input positive double!");
                System.out.println("Enter again: ");
            }
        }
    }

    public static String checkInputDate() {
        while (true) {
            try {
                String result = sc.nextLine();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                Date parseExpiredDay = sdf.parse(result);
                return result;
            } catch (NumberFormatException | ParseException e) {
                System.err.println("Input correct format date <dd/MM/yyyy>!");
                System.out.print("Enter again: ");
            }
        }
    }

}
