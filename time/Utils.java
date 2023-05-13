import java.util.*;

public class Utils
{
    private Scanner scanner = new Scanner(System.in);
    public int getUserInputInt(String prompt) {
      while (true) {
          System.out.print(prompt);
          try {
              return Integer.parseInt(scanner.nextLine());
          } catch (NumberFormatException e) {
              System.out.println("Invalid input. Please enter an integer.");
          }
      }
    }
    
    public String getUserInputString(String prompt) {
      System.out.print(prompt);
      return scanner.nextLine();
    }
}
