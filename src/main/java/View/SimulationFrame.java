package View;

import Model.Scheduler;
import Model.Server;
import Model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SimulationFrame extends JFrame {
    Scheduler s;
    public SimulationFrame(Scheduler s1){
        s=s1;
        List<Server> serv=s1.getServers();
        for(int i=0;i<serv.size();i++){
            JLabel jlabel = new JLabel("Q");
            jlabel.setFont(new Font("Verdana",1,20));
            jlabel.setBounds(10,10+i*25,50,50);
            add(jlabel);
            BlockingQueue<Task> t=serv.get(i).getTasks();
            for(int j=1;j<=t.size();j++){
                JLabel jlabel1 = new JLabel("C");
                jlabel1.setFont(new Font("Verdana",1,20));
                jlabel1.setBounds(10+j*30,10+i*25,50,50);
                this.add(jlabel1);
            }
        }
        setLayout(null);
        this.setTitle("Simulation frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void update(Scheduler s1){
        JPanel p=new JPanel();
        p.setLayout(null);
        List<Server> serv=s1.getServers();
        for(int i=0;i<serv.size();i++){
            JLabel jlabel = new JLabel("Q");
            jlabel.setFont(new Font("Verdana",1,20));
            jlabel.setBounds(10,10+i*25,50,50);
            p.add(jlabel);
            BlockingQueue<Task> t=serv.get(i).getTasks();
            for(int j=1;j<=t.size();j++){
                JLabel jlabel1 = new JLabel("C");
                jlabel1.setFont(new Font("Verdana",1,20));
                jlabel1.setBounds(10+j*30,10+i*25,50,50);
                p.add(jlabel1);
            }
        }
        this.setContentPane(p);
        this.setVisible(true);
    }
}
