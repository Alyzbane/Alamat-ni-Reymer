import java.io.*;
import java.util.*;

public class User
{
  private final String USER_FILE = "user.dat";
  private Map<String, String> users;
  private String login_usr;
  private String pass;
  private Utils util; 
  private boolean ragn;

  public User()
  {
    users = new HashMap<String, String>();
    login_usr = "";
    pass = "";
    util = new Utils();
    ragn = true;
  }

  public String getUser()
  {
    return login_usr;
  }
  public int login_menu()
  {
     int option;
     do {
       if(ragn) 
           //will only run again if 
           //the user or std reg again
       {
           ragn = false;
           loadUsers();
       }
       System.out.println("Welcome to Student Time in & Time Out System");
       System.out.println
        (
        "+-------------------------------+\n" +
        "| [1] - Login              \t|\n" +
        "| [2] - Register Student ID\t|\n" +
        "| [0] - Exit               \t|\n" +
        "+-------------------------------+\n"
        );            
            option = util.getUserInputInt("\n> ");
            if(option == 0)
            {
                System.out.println("Exiting...");
                return 0;
            }

            util.press_key();
            switch (option) {
                case 1:
                    if(login()) return option;
                    break;
                case 2:
                    register();
                    break;
               default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
            util.press_key();
     } while (option != 0);
     return option;
  }
  private void loadUsers() {
    try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length != 2) {
                System.err.println("Error: Invalid format in user file.");
                continue;
            }
            users.put(parts[0], parts[1]);
        }
    } catch (IOException e) {
        System.err.println("Error: Unable to read user file.");
    }
} 

    private boolean login() {
        System.out.println("Login\n");
        String username = util.getUserInputString("Enter your Student ID: ");
        String password = util.askPass();
        if(authInput(username, password, 1)) return true;
        return false;
    }

    public boolean authUser()
    {
        System.out.println("To proceed, verify your username and password first\n");
        String usrn = util.getUserInputString("Enter username: ");
        String pasd = util.askPass();
        if(authInput(usrn, pasd, 3)) 
        {
            return true;
        }
        return false;
    }

    public boolean authInput(String username, String password, int state)
    {
        boolean valid = true;
        switch(state)
        {
            case 1:
            if (users.containsKey(username) && users.get(username).equals(password)) {
              login_usr = username;
              pass = password;
              return valid;
            }
            System.out.println("Invalid username or password.");
            return !valid;

           case 2:
           if (username.isEmpty() || password.isEmpty()) {
             System.out.println("Error: Username and password cannot be empty.");
             return !valid;
            }
          if (users.containsKey(username)) {
          System.out.println("Error: Username already exists.");
          return !valid;
           }
          return valid;
          case 3:
          if(username.equals(login_usr) && password.equals(pass))
          {
              System.out.println("Authentication is verified successfully");
              return valid;
          }
          System.out.println("Authentication of user failed...");
          return !valid;

          default:
            break;
        }
        System.out.println("AuthUser : 86 - Invalid option");
        return !valid;
    }

    private void register() {
    System.out.println("Register Student Information");
    String username = util.getUserInputString("Enter your Student ID: ");
    String password =  util.askPass();
    
    if(!authInput(username, password, 2)) return;
    users.put(username, password);
    try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE, true))) {
        writer.println(username + " " + password);
        ragn = true;
        System.out.println("Registration successful!");
    } catch (IOException e) {
        System.err.println("Error: Unable to write to user file.");
    }
  }
}
