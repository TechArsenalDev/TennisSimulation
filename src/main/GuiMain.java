package main;

import javax.swing.*;
import java.util.Objects;


public class GuiMain {

    private Main main;

    private JPanel panelMain;

    private JTextField textFieldVelocity;
    private JTextField textFieldAngle;
    private JTextField textFieldHeight;

    private JButton buttonCalculate;
    private JCheckBox checkBoxNet;
    private JCheckBox checkBoNoDrag;
    private JCheckBox checkBoxDrag;
    private JCheckBox checkBoxMagnusEffect;
    private JTextField textFieldRotationSpeed;
    private JTextField textFieldDistance;


    GuiMain()
    {

        main = new Main();

        buttonCalculate.addActionListener(e -> {
            String v = textFieldVelocity.getText();
            String ang = textFieldAngle.getText();
            String h = textFieldHeight.getText();
            String x = textFieldDistance.getText();
            String rotationspeed = textFieldRotationSpeed.getText();
            boolean net = checkBoxNet.isSelected();
            boolean noDrag = checkBoNoDrag.isSelected();
            boolean drag = checkBoxDrag.isSelected();
            boolean magnus = checkBoxMagnusEffect.isSelected();

            if(Objects.equals(v, "0") || !noDrag && !drag && !magnus)
                JOptionPane.showMessageDialog(null, "Es kann keine Flugbahn simuliert werden. \nStartgeschwindigkeit und Flugbahnen überprüfen!","Fehler", JOptionPane.ERROR_MESSAGE);
            else
                main.receiveVariables(Double.parseDouble(v), Double.parseDouble(ang), Double.parseDouble(h), Double.parseDouble(x), Double.parseDouble(rotationspeed), net, noDrag, drag, magnus);
        });
    }

    public static void main(String[] args)  {
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        JFrame frame = new JFrame("Tennis Simulation");
        frame.setContentPane(new GuiMain().panelMain);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocation(60, 60);

    }

    public void createUIComponents()
    {

    }
}
