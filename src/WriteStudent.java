import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteStudent {
    public static void main(String []args){
        try{
            String fileName = "student.dat";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            ArrayList<Student> list = new ArrayList<>();
            list.add(new Student("SE150000", "Hoa Doan"));
            list.add(new Student("SE150021", "Thang Nguyen"));
            list.add(new Student("SE150011", "Thanh Tran"));
            list.add(new Student("SE150005", "Huong Nguyen"));
            list.add(new Student("SE150004", "Hoang Le"));
            list.add(new Student("SE150010", "Nam Trieu"));
            list.add(new Student("SE150022", "Hai Than"));
            list.add(new Student("SE150030", "Van Ly"));
            list.add(new Student("SE150025", "Son Ho"));
            list.add(new Student("SE150015", "Huy Nguyen"));

            for(Student st : list){
                oStream.writeObject(st);
            }
            oStream.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Somethings went wrong!");
        } catch (IOException e) {
        }
    }
}
