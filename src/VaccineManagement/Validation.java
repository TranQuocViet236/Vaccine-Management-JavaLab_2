package VaccineManagement;

import ListClass.Injection;
import ListClass.Student;
import ListClass.Vaccine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    List<Injection> injectionList = new ArrayList<>();
    List<Student> studentList = new ArrayList<>();
    List<Vaccine> vaccineList = new ArrayList<>();
    int isContinue() {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        int result = -1;
        do {
            System.out.print("Do you want to continue (y/n)?: ");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                result = 1;
                cont = false;
            }
            else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                result = 0;
                cont = false;
            }
            else {
                cont = true;
            }
        } while (cont);
        return result;
    }

    int isContinue(String message) {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        int result = -1;
        do {
            System.out.print(message);
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                result = 1;
                cont = false;
            }
            else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
                result = 0;
                cont = false;
            }
            else {
                cont = true;
            }
        } while (cont);
        return result;
    }

    String checkStr(String message){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print(message);
            String string = scanner.nextLine();
            if(!string.isEmpty())
                return string;
            System.out.println("Input is empty");
        }
    }

    String checkPlace(String message){
        String[] provinces = {"AnGiang", "BaRia–VungTau", "BacLieu", "BacGiang", "BacKan", "BacNinh", "BenTre",
                "BinhDuong", "BinhDinh", "BinhPhuoc", "BinhThuan", "CaMau", "CaoBang", "CanTho",
                "DaNang", "DakLak", "DakNong", "DienBien", "DongNai", "DongThap", "GiaLai", "HaGiang", "HaNam",
                "HaNoi", "HaTinh", "HaiDuong", "HaiPhong", "HauGiang", "HoaBinh",
                "HoChiMinh", "SaiGon", "HungYen", "KhanhHoa", "KienGiang", "KonTum", "LaiChau", "LangSon", "LaoCai",
                "LamDong", "LongAn", "NamDinh", "NgheAn", "NinhBinh", "NinhThuan",
                "PhuTho", "PhuYen", "QuangBinh", "QuangNam", "QuangNgai", "QuangNinh", "QuangTri",
                "SocTrang", "SonLa", "TayNinh", "ThaiBinh", "ThaiNguyen", "ThanhHoa", "ThuaThienHue","Hue",
                "TienGiang", "TraVinh", "TuyenQuang", "VinhLong", "VinhPhuc", "YenBai"};
        while (true){
            try {
                System.out.print(message);
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                for (int i = 0; i < provinces.length; i++) {
                    if(input.equalsIgnoreCase(provinces[i])){
                        return input;
                    }
                }
                System.out.println("Invalid location");
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    String checkDate(String message){
        boolean check = true;
        String expired_date= "";
        System.out.print(message);
        do{
            try{
                Scanner sc = new Scanner(System.in);
                expired_date = sc.nextLine();
                if(!isValidDate(expired_date)) throw new Exception("Date entered is incorrect");
                check = false;
            }catch (Exception e){
                System.out.println(e.getMessage());
                check = true;
                System.out.println("Please Enter Date again");
            }
        }while(check);
        return expired_date;
    }

    String checkSecondDate(String firstDate){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            long date = dateFormat.parse(firstDate).getTime();
            while(true){
                String secondDate = checkDate("Enter Second Date (dd/mm/yyyy): ");
                long math = dateFormat.parse(secondDate).getTime();
                math -= date;
                math /= 86400000;
                if(math >= 7*4 && math<=7*12) return secondDate;
                System.out.println(math);
                System.out.println("You need 4 to 12 weeks for the second vaccination");
                if(isContinue("Do you want enter second date again (y/n)?") == 0) return null;
            }
        }catch(Exception e){
            System.out.println("Error: " +e);
        }
        return null;
    }

    public static Pattern dateRegexPattern = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)");
    public static boolean isValidDate(String dateString) {
        Matcher dateMatcher = dateRegexPattern.matcher(dateString);
        if (dateMatcher.matches()) {
            dateMatcher.reset();
            if (dateMatcher.find()) {
                String day = dateMatcher.group(1);
                String month = dateMatcher.group(2);
                int year = Integer.parseInt(dateMatcher.group(3));
                if ("31".equals(day) &&
                        ("4".equals(month) || "6".equals(month) || "9".equals(month) ||
                                "11".equals(month) || "04".equals(month) || "06".equals(month) ||
                                "09".equals(month))) {
                    return false; // 1, 3, 5, 7, 8, 10, 12 has 31 days
                } else if ("2".equals(month) || "02".equals(month)) {
                    //leap year
                    if (year % 4 == 0) {
                        return !"30".equals(day) && !"31".equals(day);
                    } else {
                        return !"29".equals(day) && !"30".equals(day) && !"31".equals(day);
                    }
                } else {
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
