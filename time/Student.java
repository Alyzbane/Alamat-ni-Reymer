import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Student {
    private String name;
    private String course;
    private int year;
    private ArrayList<LocalDateTime> timeInHistory;
    private ArrayList<LocalDateTime> timeOutHistory;

    public Student() {
        this.name = "";
        this.course = "";
        this.year = 0;
        this.timeInHistory = new ArrayList<>();
        this.timeOutHistory = new ArrayList<>();
    }

    public Student(String name, String course, int year) {
        this.name = name;
        this.course = course;
        this.year = year;
        this.timeInHistory = new ArrayList<>();
        this.timeOutHistory = new ArrayList<>();
    }

    public String getname()
    {
        return this.name;
    }
    public String getcs()
    {
        return this.course;
    }
    public int getyr()
    {
        return this.year;
    }
    public void getTimeInHistory() {
        System.out.println("*** Time In ***");
       System.out.println("+-------------------------------+");


        if(timeInHistory.size() == 0) 
            System.out.println("|\t No Data \t\t|");
        else
        {
            for(var ldt : timeInHistory)
            {
             String fd = dtf(ldt);

             System.out.println("|\t" + fd + "\t|");
            }
        }
       System.out.println("+-------------------------------+");
    }

    public void getTimeOutHistory() {
        System.out.println("*** Time out ***");
       System.out.println("+-------------------------------+");
        if(timeInHistory.size() == 0) 
            System.out.println("|\t No Data \t\t|");
        else
        {
         for(var ldt : timeOutHistory)
         {
            String fd = dtf(ldt);
            System.out.println("|\t" + fd + "\t|");
         }
        }
       System.out.println("+-------------------------------+");

    }
    private void listAllTime()
    {

    System.out.println("Time In/Out History");
    System.out.println("+--------------------------------------------------------------------------------+");
    System.out.println("|\t\t Time In \t\t|\t\t Time Out  \t\t|");
    System.out.println("+--------------------------------------------------------------------------------+");

    int maxSize = Math.max(timeInHistory.size(), timeOutHistory.size());
    int tih = timeInHistory.size();
    int toh = timeInHistory.size();

    if(tih == 0 && toh == 0)
    {
       System.out.println("|\t\t No Data \t\t|\t\t No Data \t\t|");
       System.out.println("+--------------------------------------------------------------------------------+");
        return;
    }

    for (int i = 0; i < maxSize; i++) {

        //apply ternary op since some cases
        //time in & out will not be equal in size so just 
        //return empty space if thats the case
        String timeIn = i < timeInHistory.size() 
                        ? dtf(timeInHistory.get(i)) : "";

        String timeOut = i < timeOutHistory.size() 
                         ? dtf(timeOutHistory.get(i)) : "";

        System.out.printf("|\t %s \t\t|\t %s \t\t|\n", timeIn, timeOut);
    }
    System.out.println("+--------------------------------------------------------------------------------+");

    }

    private String dtf(LocalDateTime ldtc)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");
        String fdt = ldtc.format(formatter);

        return fdt;
    }

public void listTime(int cc) {
    switch(cc)
    {
        case 1: 
            getTimeInHistory();
            break;
        case 2:
            getTimeOutHistory();
            break;
        case 3:
            listAllTime();
            break;
        default:
            break;
    }
}
    

    public void timeInStudent() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("Time in at " + dtf(ldt));
        this.timeInHistory.add(ldt);
    }

    public void timeOutStudent() {
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("Time out at " + dtf(ldt));
        this.timeOutHistory.add(ldt);
    }

    public void update(Student student)
    {

         if (student != null) {
             this.name = student.getname();
             this.course = student.getcs();
             this.year = student.getyr();
         } else {
             System.out.println("Student does not exist");
         }
     }
}
