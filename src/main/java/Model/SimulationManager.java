package Model;

import Model.Task;
import View.SimulationFrame;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable{
    public int timeSimulation;
    public int maxServiceTime;
    public int minServiceTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int nrOfServices;
    public int nrOfClients;
    public SelectionPolicy selectionPolicy= SelectionPolicy.SHORTEST_TIME;
    private Scheduler scheduler;
    private SimulationFrame frame;
    private List<Task> generatedTasks=new ArrayList<>();


    public SimulationManager(int timeS,int maxS,int minS,int maxA,int minA,int nrS,int nrC){
        timeSimulation=timeS;
        maxArrivalTime=maxA;
        minArrivalTime=minA;
        maxServiceTime=maxS;
        minServiceTime=minS;
        nrOfServices=nrS;
        nrOfClients=nrC;
        scheduler=new Scheduler(nrS,timeS);
        List<Server> serverList=scheduler.getServers();
        for(Server serv:serverList){
            Thread t=new Thread(serv);
            t.start();
        }
        frame=new SimulationFrame(scheduler);
        scheduler.changeStrategy(SelectionPolicy.SHORTEST_TIME);
        generateNRandomTasks();
    }

    private void generateNRandomTasks(){
        Random rand=new Random();
        for(int i=0;i<nrOfClients;i++) {
            int randomServiceTime = rand.nextInt((maxServiceTime - minServiceTime) + 1) + minServiceTime;
            int randomArrivalTime = rand.nextInt((maxArrivalTime - minArrivalTime) + 1) + minArrivalTime;
            Task t=new Task(i+1,randomArrivalTime,randomServiceTime);
            generatedTasks.add(t);
        }
    }

    @Override
    public void run() {
        int currentTime = 0;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("log.txt", false));
            writer.write(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (currentTime < timeSimulation) {
            for (int i = 0; i < generatedTasks.size(); i++) {
                if (generatedTasks.get(i).getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(generatedTasks.get(i));
                    generatedTasks.remove(generatedTasks.get(i));
                    i--; }
            }
            frame.update(scheduler);
            try {
                writer.append("\n\n Time " + currentTime + "\n");
                writer.append("Waiting clients:");
                for (Task task : generatedTasks) {
                    writer.append(task.afisare());
                }
                List<Server> servers = scheduler.getServers();
                for (int i = 0; i < nrOfServices; i++) {
                    writer.append("\n Queue" + i);
                    BlockingQueue<Task> clienti = servers.get(i).getTasks();
                    for (Task task : clienti) {
                        writer.append(task.afisare());
                    }
                } } catch (IOException e) {
                e.printStackTrace();
            }
                currentTime++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.dispose();
    }

    public List<Task> getGeneratedTasks() {
        return generatedTasks;
    }
}
