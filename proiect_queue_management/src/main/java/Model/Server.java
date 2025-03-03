package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable
{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private int currentTaskProcessingTime;
    private int currentTaskArrivalTime;
    private int currentTaskID;
    private int serverID;
    static private int IDCounter=0;

    public int getServerID() {
        return serverID;
    }

    public Server()
    {
        this. serverID=IDCounter;
        IDCounter++;
        tasks = new LinkedBlockingQueue<>();
        waitingPeriod = new AtomicInteger(0);
    }
    public void addTask(Task newTask)
    {
        tasks.add(newTask);
        waitingPeriod.getAndAdd(newTask.getServiceTime());
    }

    public void run()
    {
        while (true)
        {
            Task task = tasks.peek();
            if (task != null)
            {
                if (task.getServiceTime() < 1)
                {
                    tasks.poll();
                    continue;
                }
                processedTask(task);
                try
                {
                    Thread.sleep(currentTaskProcessingTime * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                waitingPeriod.getAndDecrement();
            }
        }
    }

    public void processedTask(Task task)
    {
        currentTaskProcessingTime = task.getServiceTime();
        currentTaskArrivalTime = task.getArrivalTime();
        currentTaskID = task.getID();
    }
    public int getCurrentTaskProcessingTime() {
        return currentTaskProcessingTime;
    }
    public Task[] getTasks()
    {
        return tasks.toArray(new Task[0]);
    }
    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

}
