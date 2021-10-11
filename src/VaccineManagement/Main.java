package VaccineManagement;

public class Main {
    public static void main(String[] args){
        Function func = new Function();
        Choice menu = new Choice();
        menu.addMenuItem("1.Show information all students have been injected");
        menu.addMenuItem("2.Add student's vaccine injection information");
        menu.addMenuItem("3.Updating information of student's vaccine injection");
        menu.addMenuItem("4.Delete student vaccine injection information");
        menu.addMenuItem("5.Search for injection information by studentID");
        menu.addMenuItem("6.Quit");
        int Number = 0;
        do {
            Number = menu.getUserChoice();
            switch (Number){
                case 1: func.showListInjection(); break;
                case 2: func.addInformation(); break;
                case 3: func.updateInformation(); break;
                case 4: func.deleteInformation(); break;
                case 5: func.searchInformation(); break;
                case 6: func.saveInformation(); break;
            }

        }while(Number>0 && Number<6);
    }
}
