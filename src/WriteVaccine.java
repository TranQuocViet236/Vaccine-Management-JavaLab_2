import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteVaccine {
    public static void main(String []args){

        try{
            String fileName = "vaccine.dat";
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);
            ArrayList <Vaccine>  list = new ArrayList<>();
            list.add(new Vaccine("Covid-V001", "AstraZeneca"));
            list.add(new Vaccine("Covid-V002", "SPUTNIK V"));
            list.add(new Vaccine("Covid-V003", "Vero Cell"));
            list.add(new Vaccine("Covid-V004", "Pfizer"));
            list.add(new Vaccine("Covid-V005", "Moderna"));
            for (Vaccine vc : list){
                oStream.writeObject(vc);
            }
            oStream.close();
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Somethings went wrong!");
        } catch (IOException e) {
        }
    }
}
