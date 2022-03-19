package Model;

import Model.Server;
import Model.Strategy;
import Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        int min=servers.get(0).getTasks().size();
        int minim=0;
        for(int i=1;i<servers.size();i++){
            if(min>servers.get(i).getTasks().size()) {
                min = servers.get(i).getTasks().size();
                minim=i;
            }
        }
        servers.get(minim).addTask(t);

    }
}

