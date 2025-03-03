package Model;

public class Task
{
    private int ID;
    private int arrivalTime;
    private int serviceTime;
    private boolean inQueue;
    public Task(int id, int arrivalTime, int serviceTime)
    {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.ID = id;
        this.inQueue=false;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getID(){
        return ID;
    }
    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void setInQueue(boolean inQueue) {
        this.inQueue = inQueue;
    }

}
