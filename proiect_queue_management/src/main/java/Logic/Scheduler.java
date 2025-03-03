package Logic;

import Model.Server;
import Model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler
{
    private List<Server> servers;
    private Strategy strategy;

    public Scheduler(int maxNrOfServers)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(maxNrOfServers);
        servers = new ArrayList<>();
        for(int i=0; i<maxNrOfServers; i++)
        {
            Server server = new Server();
            servers.add(server);
            executorService.execute(server);
        }
    }
    public void changeStrategy(SelectionPolicy policy)
    {
        if (policy == SelectionPolicy.SHORTEST_QUEUE)
        {
            strategy = new ConcreteStrategyQueue();
        }
        else if (policy == SelectionPolicy.SHORTEST_TIME)
        {
            strategy = new ConcreteStrategyTime();
        }
    }
    public void dispatchTask(Task task)
    {
        strategy.addTask(servers, task);
    }

    public List<Server> getServers()
    {
        return servers;
    }

}
