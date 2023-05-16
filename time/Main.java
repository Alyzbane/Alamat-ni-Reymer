import java.time.LocalDateTime;
import java.util.HashMap;
 
public class Main {
 
    private static HashMap<String, Student> students = new HashMap<>();
    private static User usr;
    private static String _uid;
    private static Utils _util;
    private static Student student;
    private static boolean mainState;

public static int showMenu() {
    _util.ClearScreen();
        showMainMenu();
        int userInput = _util.getUserInputInt("\n\n> ");
        _util.press_key();
        if(userInput == 0) return -1;// case 0 user wishes to exit 
        
        switch(userInput) {
            case 1:
                _uid = _util.getUserInputString("Search Student ID: ");
                searchStudent(_uid);
                break;
            case 2:
               int choice = getTimeMenu();
               if(choice ==  0) return 0;
               break;
            case 3:          
                student.timeInStudent();
                break;
            case 4:
                student.timeOutStudent();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                askStudent();
                break;
            case 7:
                student.showInfo();
                break;
            default:
              System.out.println("Invalid input. Please try again.\n");
              return 0;
        }
        return userInput;
    }
  
    public static void searchStudent(String uid)
    {
        if(students.containsKey(uid))
            System.out.println("Student ID: " + uid + " exist"); 
        else 
            System.out.println("Student ID: " + uid + " does not exist");
    }

    public static void deleteStudent()
    {
        System.out.println("Reminder: Removing a student ID is only applied to your own Student ID.\n");
        System.out.println("Please fill in the fields to verify this is your own Student ID");

        if(usr.authUser()) 
        {
            _util.press_key();
            if(_util.getUserInputInt("Warning: Student ID [" + _uid + "] would be really deleted in the database\n Continue?\n[1] - Yes\n[2] - No\n> ") == 1)
            {
             students.remove(_uid); 
             usr.removeUser(_uid);
             usr.updateFile();
             mainState = true;
             System.out.println("Student ID [ " + _uid + " ] is deleted");
            }
            else System.out.println("Delete operation aborted.");
        }
    }

    public static void showMainMenu()
    {
        System.out.println("Main Menu");
       System.out.println
        (
        "+-------------------------------+\n" +
        "| [1] - Search Student ID \t|\n" +
        "| [2] - List Time         \t|\n" +
        "| [3] - Time in           \t|\n" +
        "| [4] - Time out          \t|\n" +
        "| [5] - Delete My Info    \t|\n" + 
        "| [6] - Update My Info    \t|\n" +
        "| [7] - Show My Info      \t|\n" +
        "| [0] - Log Out           \t|\n" +
        "+-------------------------------+\n"
        );
    }


    public static int getTimeMenu()
{
    listMenu();
    int choice = _util.getUserInputInt("> ");
    if(choice == 0) return 0;

    while(choice != 0)
    {
    _util.press_key();
    switch(choice)
    {
        case 1: 
            student.getTimeInHistory();
            break;
        case 2:
            student.getTimeOutHistory();
            break;
        case 3:
            student.listAllTime();
            break;
        case 0:
            return 0;
        default:
            System.out.println("Invalid Option");
            break;
    }
    _util.press_key();
    listMenu();
    choice = _util.getUserInputInt("> ");
    }
    return choice;

}

    public static void listMenu()
    {

    System.out.println("+-- Time In & Time Out Menu ---+\n");
    System.out.println(
    "+------------------------+\n" + 
    "| [1] - Time In History  |\n" + 
    "| [2] - Time Out History |\n" +
    "| [3] - Both Time History|\n" +
    "| [0] - Return           |\n" +
    "+------------------------+");

    }
    public static void askStudent()
    {

        if(!usr.authUser()) return;
        if(_util.getUserInputInt
                ("Warning: Student ID [" +
                 _uid + "] would be really " +
                 "deleted in the database\n" +
                 "Continue?\n[1] - Yes\n[2] - No\n> ") != 1)
        {
            System.out.println("Change Student Information aborted.");
            return;
        }
        while(true)
        {

           //add the student id 
           System.out.println("Enter new student data");
           String sid = _util.getUserInputString("Enter student ID: ");
           String pass = _util.askPass();
           if (!usr.authInput(sid, pass, 2)) 
           {
             System.out.println("\nError: Invalid student ID.");
           }
           else if(pass.isEmpty())
           {
             System.out.println("\n\nError: Invalid password.");
           }
           else 
           {
               Student nstd = new Student(
                       sid,
                       student.getTIH(),
                       student.getTOH()
                       ); //new student
               System.out.println("Student ID successfully updated");
               students.remove(_uid); //removing the old data
               students.put(sid, nstd); //passing the new data of std
               usr.updateMap(sid, pass); //updating the map values
               usr.updateFile();  //updating the file values
               _uid = sid; //new uid of user
               mainState = true;
               return;
           }
           System.out.println("\n--- Please enter a valid student data ---\n");
           _util.press_key();
        }
    }

    private static void logging(String prompt)
    {
          System.out.println(prompt); 
    }

    private static boolean isValidID(String uid) 
    {
       if(!students.containsKey(uid))
           return false; //student id not here
       return true; //its here
    }

 
    public static void main(String []args) {
        usr = new User();
        _util = new Utils();
        _util.ClearScreen();
        _util.WelcomeScreen();
        _util.press_key();

        _util.ClearScreen();
        int cmd = usr.login_menu();
        _util.press_key();

        student = new Student();
        mainState = false; //if the user made changes into
                           //their details then they will exit into login intf
        while(cmd != 0)
        {
            _uid = usr.getUser();
            if(!isValidID(_uid)) students.put(_uid, student);
            student = students.get(_uid);
            student.setSID(_uid);

            while(showMenu() != -1) 
            {
                if(mainState == true)
                {
                    System.out.println
                        ("\n\n+--- Returning into main interface...");
                    _util.press_key();
                    mainState = false; //reset the mainState
                    break;
                }
                _util.press_key();
            }

            _uid = "";
            student = new Student();

            _util.ClearScreen();
            cmd = usr.login_menu();
            _util.press_key();
       }
    }
}
