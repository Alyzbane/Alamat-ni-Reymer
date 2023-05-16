import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Student {
    private String sid;
    private ArrayList<LocalDateTime> timeInHistory;
    private ArrayList<LocalDateTime> timeOutHistory;

    public Student() {
        this.sid = "";
        this.timeInHistory = new ArrayList<>();
        this.timeOutHistory = new ArrayList<>();
    }

    public void showInfo()
    {
        System.out.println("+--- Student Information");
        System.out.println("+--- Student ID: " + this.sid);
    }

    public Student(String sid)
    {
        this.sid = sid;
        this.timeInHistory = new ArrayList<>();
        this.timeOutHistory = new ArrayList<>();
    }

    public Student(String sid, ArrayList<LocalDateTime> tih,
        ArrayList<LocalDateTime> toh)
    {
        this.sid = sid;
        this.timeInHistory = new ArrayList<>();
        this.timeOutHistory = new ArrayList<>();
        setTimeIn(tih);
        setTimeOut(toh);
    }


    //setter time
    public void setTimeIn(ArrayList<LocalDateTime> tih)
    {

        if(tih != null) this.timeInHistory.addAll(tih);
    }
    public void setTimeOut(ArrayList<LocalDateTime> toh)
    {
        if(toh != null) this.timeOutHistory.addAll(toh);
    }
    //end of setter time

    //getter of time
    public ArrayList<LocalDateTime> getTIH()
    {
        return this.timeInHistory;
    }
    public ArrayList<LocalDateTime> getTOH()
    {
        return this.timeOutHistory;
    }
    //end of getter time

    public String getSID()
    {
        return this.sid;
    }

    public void setSID(String uid)
    {
        this.sid = uid;
    }

    public void getTimeInHistory() {
       showInfo();
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
        showInfo();
        System.out.println("+-------------------------------+");
        if(timeOutHistory.size() == 0) 
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
    public void listAllTime()
    {
    showInfo();
    getTimeInHistory();
    getTimeOutHistory();
    System.out.println("+--- Time In/Out History ---+\n\n");
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

    public String update(Student student)
    {
         if (student != null) {
             this.sid = student.getSID();
             return sid;
         }

         System.out.println("Student does not exist\n");
         return "";
     }
}
