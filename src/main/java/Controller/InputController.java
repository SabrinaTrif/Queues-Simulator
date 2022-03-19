package Controller;

import Model.SimulationManager;
import Model.Task;
import View.InputFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InputController {
    private InputFrame in;
    public InputController(InputFrame view){
        in=view;
        view.addSubListener(new SubListener());
    }
    class SubListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
             SimulationManager gen=new SimulationManager(Integer.parseInt(in.getTimeS().getText()),Integer.parseInt(in.getMaxS().getText()),Integer.parseInt(in.getMinS().getText()),Integer.parseInt(in.getMaxA().getText()),Integer.parseInt(in.getMinA().getText()),Integer.parseInt(in.getNrQ().getText()),Integer.parseInt(in.getNrC().getText()));
            List<Task> s=gen.getGeneratedTasks();
            for(Task task:s){
              System.out.println(task.afisare());
             }
            Thread t= new Thread(gen);
            t.start();
            in.dispose();
        }
    }
}
