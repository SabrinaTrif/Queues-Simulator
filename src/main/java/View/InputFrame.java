package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputFrame extends JFrame{
    private JTextField timeS = new JTextField(10);
    private JTextField maxS = new JTextField(10);
    private JTextField maxA = new JTextField(10);
    private JTextField minS = new JTextField(10);
    private JTextField minA = new JTextField(10);
    private JTextField nrQ= new JTextField(10);
    private JTextField nrC= new JTextField(10);
    private JButton submit=new JButton("Submit");

    public InputFrame(){
        JPanel content = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        p1.add(new JLabel("Simulation time"));
        p1.add(timeS);
        p1.setLayout(new FlowLayout());
        p2.add(new JLabel("Min arrival time"));
        p2.add(minA);
        p2.add(new JLabel("Max arrival time"));
        p2.add(maxA);
        p2.setLayout(new FlowLayout());
        p3.add(new JLabel("Min service time"));
        p3.add(minS);
        p3.add(new JLabel("Max service time"));
        p3.add(maxS);
        p3.setLayout(new FlowLayout());
        p4.add(new JLabel("Nr of clients"));
        p4.add(nrC);
        p4.add(new JLabel("Nr of services"));
        p4.add(nrQ);
        p4.setLayout(new FlowLayout());
        p5.add(submit);
        p5.setLayout(new FlowLayout());

        content.add(p1);
        content.add(p2);
        content.add(p3);
        content.add(p4);
        content.add(p5);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        this.setContentPane(content);
        this.setTitle("Inputs");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    public void addSubListener(ActionListener mal) {
        submit.addActionListener(mal);
    }

    public JTextField getMaxA() {
        return maxA;
    }

    public JTextField getMaxS() {
        return maxS;
    }

    public JTextField getTimeS() {
        return timeS;
    }

    public JTextField getMinS() {
        return minS;
    }

    public JTextField getMinA() {
        return minA;
    }

    public JTextField getNrQ() {
        return nrQ;
    }

    public JTextField getNrC() {
        return nrC;
    }
}
