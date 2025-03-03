package Logic;

import GUI.View;
import Model.Task;
import Model.Server;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SimulationManager implements Runnable
{
    private int timeLimit;
    private List<Task> generatedTasks;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private int numberOfClients;
    private int numberOfQueues;
    private View view;
    Scheduler scheduler;
    SelectionPolicy sel;
    private BufferedWriter writer;
    private FileWriter wr;
    public SimulationManager(View view, int timeLimit, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int numberOfClients, int numberOfQueues, SelectionPolicy sel)
    {
        try
        {
            this.wr = new FileWriter("output.txt");
            this.writer = new BufferedWriter(wr);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        this.view=view;
        this.numberOfQueues=numberOfQueues;
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.numberOfClients = numberOfClients;
        scheduler=new Scheduler(numberOfQueues);
        generatedTasks = new ArrayList<>();
        scheduler.changeStrategy(sel);
        generateRandomTasks();
    }

    private void generateRandomTasks()
    {
        Random random = new Random();
        try
        {
            for (int i = 0; i < numberOfClients; i++)
            {
                int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
                int serviceTime = random.nextInt(maxServiceTime - minServiceTime + 1) + minServiceTime;
                Task task = new Task(i+1, arrivalTime, serviceTime);
                generatedTasks.add(task);
                writer.write(task.getID() + " " + task.getArrivalTime() + " " + task.getServiceTime());
                writer.newLine();
                //System.out.println(task.getID() + " " + task.getArrivalTime() + " " + task.getServiceTime());
            }

            Collections.sort(generatedTasks, Comparator.comparingInt(Task::getArrivalTime));
            writer.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        int currentTime = 0;
        while (currentTime < timeLimit)
        {
            boolean allTasksProcessed = true;

            for (Task task : generatedTasks)
            {
                if (task.getArrivalTime() <= currentTime && !task.isInQueue())
                {
                    scheduler.dispatchTask(task);
                    task.setInQueue(true);
                }
                if (task.getServiceTime() > 0)
                {
                        allTasksProcessed = false;
                }
            }
                printInTxt(currentTime);
                currentTime++;
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (allTasksProcessed)
                {
                    break;
                }
        }
    }

    private void printInTxt(int currentTime)
    {
        StringBuilder waitingLineText = new StringBuilder();
        StringBuilder timeText = new StringBuilder("Time: " + currentTime + "\n");

        try
        {
            writer.newLine();
            writer.write("Waiting line:");
            writer.newLine();
            for (Task task : generatedTasks)
            {
                if (!task.isInQueue())
                {
                    writer.write("(" + task.getID() + "," + task.getArrivalTime() + "," + task.getServiceTime() + ")" + " " );
                    waitingLineText.append("(").append(task.getID()).append(",").append(task.getArrivalTime()).append(",").append(task.getServiceTime()).append(") ");
                }
            }

            writer.newLine();
            writer.newLine();
            writer.write("Timp: " + currentTime);
            writer.newLine();

            String[] queueStatus = new String[numberOfQueues];

            for (int i = 0; i < numberOfQueues; i++)
            {
                Server server = scheduler.getServers().get(i);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("queue").append(server.getServerID()).append("= ");

                boolean firstTaskPrinted = false;

                for (Task task : server.getTasks())
                {
                    if (task.getServiceTime() > 0)
                    {
                        stringBuilder.append("(").append(task.getID()).append(",").append(task.getArrivalTime())
                                .append(",").append(task.getServiceTime()).append(")");

                        if (firstTaskPrinted)
                        {
                            stringBuilder.append(" ");
                        }
                        else
                        {
                            firstTaskPrinted = true;
                            task.setServiceTime(task.getServiceTime() - 1);
                        }
                    }
                }
                queueStatus[i] = stringBuilder.toString();

                writer.write(stringBuilder.toString());
                writer.newLine();
            }
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    view.updateQueueTextFields(queueStatus);
                    view.updateWaitingLineText(waitingLineText.toString());
                    view.updateTimeText(timeText.toString());
                }
            });
            writer.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        View view = new View();
    }
}
