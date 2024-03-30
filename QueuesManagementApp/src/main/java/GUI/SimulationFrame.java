package GUI;

import BusinessLogic.Input;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimulationFrame extends JFrame {
    public static ArrayList<Integer> array;
    public JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    public JTextField NrClienti, NrCozi, TMinArrival, TMaxArrival, TMaxSimulation, TMinService, TMaxService, result;
    public JButton buton;

    public SimulationFrame() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(950, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        label1 = new JLabel("N =");
        label1.setBounds(10, 20, 30, 25);
        panel.add(label1);

        NrClienti = new JTextField();
        NrClienti.setBounds(35, 20, 100, 25);
        panel.add(NrClienti);

        label2 = new JLabel("Q =");
        label2.setBounds(10, 60, 30, 25);
        panel.add(label2);

        NrCozi = new JTextField();
        NrCozi.setBounds(35, 60, 100, 25);
        panel.add(NrCozi);

        label3 = new JLabel("TMinArrival  =");
        label3.setBounds(180, 20, 100, 25);
        panel.add(label3);

        TMinArrival = new JTextField();
        TMinArrival.setBounds(270, 20, 100, 25);
        panel.add(TMinArrival);

        label4 = new JLabel("TMaxArrival  =");
        label4.setBounds(180, 60, 100, 25);
        panel.add(label4);

        TMaxArrival = new JTextField();
        TMaxArrival.setBounds(270, 60, 100, 25);
        panel.add(TMaxArrival);

        label5 = new JLabel("TMaxSimulation =");
        label5.setBounds(10, 110, 120, 25);
        panel.add(label5);

        TMaxSimulation = new JTextField();
        TMaxSimulation.setBounds(120, 110, 100, 25);
        panel.add(TMaxSimulation);

        label6 = new JLabel("TMinService  =");
        label6.setBounds(400, 20, 100, 25);
        panel.add(label6);

        TMinService = new JTextField();
        TMinService.setBounds(500, 20, 100, 25);
        panel.add(TMinService);

        label7 = new JLabel("TMaxService  =");
        label7.setBounds(400, 60, 100, 25);
        panel.add(label7);

        TMaxService = new JTextField();
        TMaxService.setBounds(500, 60, 100, 25);
        panel.add(TMaxService);

        buton = new JButton("START");
        buton.setBounds(630, 23, 100, 60);
        panel.add(buton);

        label8 = new JLabel("LOG OF EVENTS: ");
        label8.setBounds(10, 150, 150, 25);
        panel.add(label8);

        result = new JTextField();
        result.setBounds(10, 180, 900, 400);
        panel.add(result);

        array = new ArrayList<>();
        buton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer N = Integer.parseInt(NrClienti.getText());
                array.add(N);
                Integer Q = Integer.parseInt(NrCozi.getText());
                array.add(Q);
                Integer TMinA = Integer.parseInt(TMinArrival.getText());
                array.add(TMinA);
                Integer TMaxA = Integer.parseInt(TMaxArrival.getText());
                array.add(TMaxA);
                Integer TMaxSim = Integer.parseInt(TMaxSimulation.getText());
                array.add(TMaxSim);
                Integer TMinS = Integer.parseInt(TMinService.getText());
                array.add(TMinS);
                Integer TMaxS = Integer.parseInt(TMaxService.getText());
                array.add(TMaxS);
                //result.setText(TMinS.toString());
            }
        });
        frame.setVisible(true);
    }
}
