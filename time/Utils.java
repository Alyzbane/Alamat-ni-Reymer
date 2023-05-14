import java.util.*;
import java.io.Console;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils
{

    private Scanner scanner = new Scanner(System.in);
    private Console csl;

    public void WelcomeScreen()
    {
        System.out.println(ReadAsciiArt("welcome.txt"));
    }
    public String ReadAsciiArt(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line.replaceAll("\\\\", "\\\\\\\\").replaceAll("/", "\\\\/")).append("\n");
            }

            bufferedReader.close();

            return stringBuilder.toString();
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return "";
    }

    public void ClearScreen()
    {
        for(int i = 0; i < 50; i++)
            System.out.println();
    }
    public void press_key()
    {
        csl = System.console();
        csl.readPassword("Press enter key to coninue...");
        ClearScreen();
    }

    public int getUserInputInt(String prompt) {
      while (true) {
          System.out.print(prompt);
          try {
              return Integer.parseInt(scanner.nextLine());
          } catch (NumberFormatException e) {
              System.err.println("Invalid input. Please enter an integer.");
          }
      }
    }
    
    public String getUserInputString(String prompt) {
      System.out.print(prompt);
      return scanner.nextLine();
    }

    public String askPass()
    {
        csl = System.console();
        if(csl == null)
        {
            System.out.println("No console available...");
            return null;
        }
        char parsw[] = csl.readPassword("Enter password: ");        
        String pass = new String(parsw);
        return pass;
    }
}
