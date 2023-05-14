import java.time.LocalDateTime;
import java.util.HashMap;
 
public class Main {
 
    private static HashMap<String, Student> students = new HashMap<>();
    final static String dne = "\nError: Student ID does not exist.\n";
    final static String rde = "\nError: Student ID already exist.\n";
    private static User usr;
    private static String _uid;
    private static Utils _util;
    private static Student student;

public static boolean verifySearch(String uid)
{
        if(isValidID(_uid)) return true;

        //the user inputted non existing uid
        //but choose functions that requires existing uid
        logging(dne);
        return false;
}
public static int showMenu() {
    _util.ClearScreen();
        showMainMenu();
        int userInput = _util.getUserInputInt("\n\n> ");
        _util.press_key();
        if(userInput == 0) return -1;// case 0 user wishes to exit 
        
        _uid = usr.getUser();
        student = students.get(_uid); 
                                                       
        switch(userInput) {
            case 1:
                _uid = _util.getUserInputString("Search Student ID: ");
                if(!verifySearch(_uid)) return 0;
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
                if(!usr.authUser())  return 0;
                deleteStudent(_uid);
                break;
            case 6:
                if(!usr.authUser()) return 0;
                Student newstd = askStudent();
                student.update(newstd);
                break;
            case 7:
                student.showInfo();
                break;
            default:
              System.out.println("Invalid input. Please try again.\n");
              return 0;
        }
        student = null;
        return userInput;
    }
  
    public static void searchStudent(String uid)
    {
        if(students.containsKey(uid))
        {
            student = students.get(uid);
            System.out.println(uid + " student ID exist"); 
        }
        else System.out.println("Student ID does not exist");
    }

    public static void deleteStudent(String uid)
    {
        if( authStudent(uid) )
        {
            _util.press_key();
            if(_util.getUserInputInt("Student ID [" + uid + "] would be really delete\n Continue?\n[1] - Yes\n[2] - No\n> ") == 1)
            {
             students.remove(uid); 
             System.out.println("Student ID " + uid + " is deleted");
            }
        }
        else System.out.println("Error: Authentication failed.");
    }
    public static boolean authStudent(String uid)
    {
        System.out.println("Reminder: Removing a student ID is only applied to your own Student ID.\n");
        System.out.println("Please fill in the fields to verify this is your own Student ID");
        
        //asking for student info
        String name = _util.getUserInputString("Name: ");
        String course = _util.getUserInputString("Course: ");
        int year = _util.getUserInputInt("Year: ");
        student = students.get(uid);
        //verification
        if(student.getname().equals(name) 
            && student.getcs().equals(course)
            && student.getyr() == year)
        {
            System.out.println("-- Successfully verified --\n");
            return true;
        }
        return false;
    }

    public static void showMainMenu()
    {
        System.out.println("Main Menu");
       System.out.println
        (
        "+----------------------\t+\n" +
        "| [1] - Search Student \t|\n" +
        "| [2] - List Time      \t|\n" +
        "| [3] - Time in        \t|\n" +
        "| [4] - Time out       \t|\n" +
        "| [5] - Delete My Info \t|\n" + 
        "| [6] - Update My Info \t|\n" +
        "| [7] - Show My Info   \t|\n" +
        "| [0] - Back           \t|\n" +
        "+----------------------\t+\n"
        );
    }


    public static int getTimeMenu()
{
    listMenu();
    int choice = _util.getUserInputInt("> ");
    if(choice == 0)
       return 0;

    Student student = students.get(_uid);

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
System.out.println("+------------------------+\n" + 
"| [1] - Time In          |\n" + 
"| [2] - Time Out         |\n" +
"| [3] - Both             |\n" +
"| [0] - Return           |\n" +
"+------------------------+");
    }
    public static Student askStudent()
    {
        System.out.println("Enter Student data");
        while(true)
        {
           //add the student id and its name and course and year
           String name = _util.getUserInputString("Enter Name: ");
           String course = _util.getUserInputString("Enter Course: ");
           int year = _util.getUserInputInt("Enter Year: ");
           if (year < 1900 || year > LocalDateTime.now().getYear()) 
           {
             System.out.println("Error: Invalid year. Please enter a year between 1900 and the current year.");
           }
           else if (!name.matches("[a-zA-Z ]+") || !course.matches("[a-zA-Z ]+")) 
           {
             System.out.println("Error: Invalid name or course. Please enter only letters and spaces.");
           }
           else 
           {
               student = new Student(name, course, year);
               return student;
           }
           System.out.println("--- Please enter a valid Student datas ---\n");
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

        int cmd = usr.login_menu();
        _util.press_key();
        student = new Student();
        while(cmd != 0)
        {
            _uid = usr.getUser();
            if(!isValidID(_uid)) 
            {
                student = askStudent();
                students.put(_uid, student);
                _util.press_key();
            }
            while(showMenu() != -1)
            {
                _util.press_key();
            }
            _util.ClearScreen();
            cmd = usr.login_menu();
        }
    }
}
