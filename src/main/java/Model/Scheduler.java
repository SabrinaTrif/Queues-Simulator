package Model;

import Model.SelectionPolicy;
import Model.Server;
import Model.Strategy;
import Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers=new ArrayList<>();
    private int maxNoServers;
    private Strategy strategy;

    public Scheduler(int maxNoServers,int timeS){
        for(int i=0;i<maxNoServers;i++)
        {
            Server s=new Server(timeS);
            servers.add(s);
            Thread t=new Thread(s);
        }
    }

    public List<Server> getServers() {
        return servers;
    }
    public void changeStrategy(SelectionPolicy policy){
        if(policy==SelectionPolicy.SHORTEST_QUEUE)
            strategy=new ConcreteStrategyQueue();
        if(policy==SelectionPolicy.SHORTEST_TIME)
            strategy=new ConcreteStrategyTime();
    }
    public void dispatchTask(Task t){
        strategy.addTask(servers,t);
    }
}
