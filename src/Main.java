import java.io.IOException;

public class Main {
    public static void main (String args[]){
        //set up menu
        Menu mnu = new Menu();
        mnu.add("_____MENU_____");
        mnu.add("1. Show information all students have been injected");
        mnu.add("2. Add student's vaccine injection information");
        mnu.add("3. Updating information of students' vaccine injection");
        mnu.add("4. Delete student vaccine injection information");
        mnu.add("5. Search for injection information by studentID");
        mnu.add("6. Quit and save");

        InjectionInfoList injectionInfoList = new InjectionInfoList();
        int userChoice;
        InjectionInfoList.readFile(injectionInfoList, "injection.dat");
        do{
            userChoice = mnu.getUserChoice();

            switch (userChoice){
                case 1: injectionInfoList.printAll(); break;
                case 2: injectionInfoList.adddInjectionInfo(); break;
                case 3: injectionInfoList.updateInjectionInfo(); break;
                case 4: injectionInfoList.deleteInjectionInfo(); break;
                case 5: injectionInfoList.searchByStudentID(); break;
                case 6: {
                    injectionInfoList.saveToFile(injectionInfoList, "injection.dat");
                    return;

                }
                default: break;
            }
        }while (userChoice>=0 && userChoice <=mnu.size());
    }
}
