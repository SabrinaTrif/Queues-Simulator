package Model;

import Model.Task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task>tasks = new LinkedBlockingQueue<>();
    private AtomicInteger waitingPeriod;
    private int timeSimulation;
    public Server(int timeS){

        waitingPeriod=new AtomicInteger();
        timeSimulation=timeS;
    }
    public void addTask(Task newTask){
        tasks.add(newTask);
        waitingPeriod.addAndGet(newTask.getServiceTime());
    }
    @Override
    public void run() {
      int currentTime=0;
      while(currentTime<timeSimulation){
          Task t=tasks.peek();
          if(t!=null){
              if(t.getServiceTime()>1){
                  t.decrementService();
                  waitingPeriod.decrementAndGet();
              }else if(t.getServiceTime()==1){
                  waitingPeriod.decrementAndGet();
                  try {
                      tasks.take();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
          try {
              Thread.sleep(1000);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          currentTime++;
      }
    }


    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

}
