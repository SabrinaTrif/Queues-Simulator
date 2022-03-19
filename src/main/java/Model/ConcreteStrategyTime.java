package Model;

import Model.Server;
import Model.Strategy;
import Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        int min=servers.get(0).getWaitingPeriod().get();
        int mini=0;
        for(int i=1;i<servers.size();i++){
            if(min>servers.get(i).getWaitingPeriod().get()) {
                min = servers.get(i).getWaitingPeriod().get();
                mini=i;
            }
        }
        servers.get(mini).addTask(t);

    }
}
