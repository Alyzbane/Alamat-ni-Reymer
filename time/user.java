import java.io.*;
import java.util.*;

public class User
{
  private final String USER_FILE = "user.dat";
  private Map<String, String> users = new HashMap<>();
  private String login_usr;
  private Utils util = new Utils();

    public String getUser()
  {
    return login_usr;
  }
  public int login_menu()
  {
     int option;
     do {
            loadUsers();
       System.out.println
        (
        "+--------------------------\t+\n" +
        "| [1] - Login              \t|\n" +
        "| [2] - Register Student ID\t|\n" +
        "| [0] - Exit               \t|\n" +
        "+--------------------------\t+\n"
        );            
            option = util.getUserInputInt("> ");
            switch (option) {
                case 1:
                    if(login()) return option;
                    break;
                case 2:
                    register();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return option;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
     } while (option != 3);
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
        String username = util.getUserInputString("Enter your Student ID: ");
        String password = util.getUserInputString("Enter your password: ");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            login_usr = username;
            return true;
        }

        System.out.println("Invalid username or password.");
        return false;
    }

    private void register() {
    System.out.println("Register Student Information");
    String username = util.getUserInputString("Enter your Student ID: ");
    String password =  util.getUserInputString("Enter your password: ");

    if (username.isEmpty() || password.isEmpty()) {
        System.out.println("Error: Username and password cannot be empty.");
        return;
    }

    if (users.containsKey(username)) {
        System.out.println("Error: Username already exists.");
        return;
    }

    users.put(username, password);
    try (PrintWriter writer = new PrintWriter(new FileWriter(USER_FILE, true))) {
        writer.println(username + " " + password);
        System.out.println("Registration successful!");
    } catch (IOException e) {
        System.err.println("Error: Unable to write to user file.");
    }
  }
}
