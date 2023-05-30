import java.util.LinkedList;
import java.util.Queue;


public class FCFSAlgorithm 
{
    public static void main(String[] args) throws InterruptedException 
    {

        Utils _util = new Utils();
        Logging logs = new Logging();
        // Get the number of processes from the user
        int numProcesses = _util.getUserInputInt("Enter the number of processes: "); 

        // Create a queue of processes
        Queue<Process> processQueue = new LinkedList<>();
        for (int i = 1; i <= numProcesses; i++) 
        {
            System.out.println("Enter details for process " + i + ":");
            int arrivalTime = _util.getUserInputInt("Arrival time: ");
            int burstTime = _util.getUserInputInt("Burst time: ");
            processQueue.add(new Process("P" + i, arrivalTime, burstTime));
            System.out.println();
        }

        _util.press_key();
        _util.ClearScreen();
        System.out.println("Running the processes...\n\n");

        int currentTime = 0; //summation of total time process is running by from 1 -> last n
          /* example:
            currentTime = 0;
            process 1:
            arrival time : 0
            burst time : 5
            completion time = 5
            turnaround time = 5
            waiting time = 0

            currenttime = 5

            process 2:
            arrival time : 1
            burst time : 2
            completion time = 7
            turnaround time = 6
            waiting time = 4

            currenttime = 7 */

        while (!processQueue.isEmpty()) 
        {
            Process currentProcess = processQueue.poll();

            int waitingTime = currentTime - currentProcess.getArrivalTime();

            if (waitingTime < 0) waitingTime = 0;

            System.out.printf("Process %s is running...\n", currentProcess.getName());
            // Simulate waiting time in seconds
            Thread.sleep(waitingTime * 1000);
            System.out.printf("Process %s executes successfully...\n", currentProcess.getName());

            int completionTime = currentTime + currentProcess.getBurstTime();
            int turnaroundTime = completionTime - currentProcess.getArrivalTime();

            String log ="\t| " + currentProcess.getName() + "\t\t| " +
                         currentProcess.getArrivalTime() + "\t\t| " + 
                         currentProcess.getBurstTime() + "\t\t| " + 
                         completionTime + " \t\t\t| " + 
                         turnaroundTime + " \t\t\t| " +
                         waitingTime + "\t\t|\n";    
            logs.addLog(log);
            currentTime = completionTime;
        }

        System.out.println("Tasks done");
        Thread.sleep(1000); //delay for 1 sec
        System.out.println("Getting the processes result...");
        Thread.sleep(1000); //delay for 1 sec
        _util.press_key();
        _util.ClearScreen();
        
        System.out.println("Processes result");
        System.out.println("\t-----------------------------------------------------------------------------------------------------------------");
        System.out.println("\t| Process\t| Arrival Time\t| Burst Time\t| Completion Time\t| Turnaround Time\t| Waiting Time  |");
        System.out.println("\t-----------------------------------------------------------------------------------------------------------------");

        logs.displayLogs();

        System.out.println("\t-----------------------------------------------------------------------------------------------------------------");

/*         System.out.println("\n\tChoose an Option\n");
        System.out.println("\t1 - [Run again]\n\t2 - [Exit the program]\n");
        int n = _util.getUserInputInt("> ");
        if(n == 1)
        {
          new FCFSAlgorithm();
        } */

        System.out.println("Program exiting...");
        return;
    }
}
