package Logic;

import Model.Server;
import Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy
{
    @Override
    public void addTask(List<Server> servers, Task newTask)
    {
        Server shortestQueue = servers.get(0);
        int shortestLength = shortestQueue.getTasks().length;

        if(shortestQueue.getCurrentTaskProcessingTime()!=0)
        {
            shortestLength++;
        }

        for (Server server : servers)
        {
            int currentLength = server.getTasks().length;

            if(server.getCurrentTaskProcessingTime()!=0)
            {
                currentLength++;
            }
            if (currentLength < shortestLength)
            {
                shortestQueue = server;
                shortestLength = currentLength;
            }
        }
        shortestQueue.addTask(newTask);
    }
}
