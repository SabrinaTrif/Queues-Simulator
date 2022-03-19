package Model;

public class Task {
    private int id;
    private int arrivalTime;
    private int serviceTime;

    public Task(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }
    public String afisare(){
        return " ("+id+","+arrivalTime+","+serviceTime+") ";
    }
    public void decrementService(){
        serviceTime-=1;
    }
}
