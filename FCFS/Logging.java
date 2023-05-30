import java.util.ArrayList;
class Logging
{
    ArrayList<String> logs;
    public Logging()
    {
        logs = new ArrayList<String>();
    }

    public void addLog(String log)
    {
        logs.add(log);
    }
    public void displayLogs()
    {
        for(String lo : logs)
        {
            System.out.print(lo);
        }
    }
}


