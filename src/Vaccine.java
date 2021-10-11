public class Vaccine {
    String vaccine_name;
    String vaccine_id;
    public Vaccine(){

    }
    public Vaccine(String vaccine_id, String vaccine_name){
        this.vaccine_name = vaccine_name;
        this.vaccine_id = vaccine_id;
    }

    public String getVaccine_name() {
        return vaccine_name;
    }

    public void setVaccine_name(String vaccine_name) {
        this.vaccine_name = vaccine_name;
    }

    public String getVaccine_id() {
        return vaccine_id;
    }

    public void setVaccine_id(String vaccine_id) {
        this.vaccine_id = vaccine_id;
    }

}
