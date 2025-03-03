/*package Logic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy
{
    @Override
    public void addTask(List<Server> servers, Task newTask)
    {
        // Find the server with the shortest total service time
        Server shortestTimeServer = servers.get(0);
        int shortestTime = getTotalServiceTime(shortestTimeServer);

        for (Server server : servers)
        {
            int currentTime = getTotalServiceTime(server);
            if (currentTime < shortestTime)
            {
                shortestTimeServer = server;
                shortestTime = currentTime;
            }
        }

        shortestTimeServer.addTask(newTask);
    }

    private int getTotalServiceTime(Server server)
    {
        int total = 0;
        for (Task task : server.getTasks())
        {
            total += task.getServiceTime();
        }
        return total;
    }
}*/

package Logic;

import Model.Server;
import Model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcreteStrategyTime implements Strategy
{
    @Override
    public void addTask(List<Server> servers, Task newTask)
    {

        Server shortestTimeServer = servers.get(0);
        AtomicInteger shortestTime = shortestTimeServer.getWaitingPeriod();
        int y= shortestTime.get();

        for (Server server : servers)
        {
            AtomicInteger currentTime =server.getWaitingPeriod();
            int x=currentTime.get();
            if (x < y)
            {
                shortestTimeServer = server;
                y = x;
            }
        }
        shortestTimeServer.addTask(newTask);
    }

}

