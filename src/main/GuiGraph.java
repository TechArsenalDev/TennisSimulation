package main;


import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GuiGraph extends JFrame{

    private ArrayList<Double> xArrayNoDrag, yArrayNoDrag, xArrayDrag, yArrayDrag, xArrayMagnus, yArrayMagnus;
    private boolean net, noDrag, drag, magnus;


    GuiGraph(ArrayList<Double> xNoDrag, ArrayList<Double> yNoDrag, ArrayList<Double> xDrag, ArrayList<Double> yDrag, ArrayList<Double> xMagnus, ArrayList<Double> yMagnus, boolean n, boolean nd, boolean d, boolean m)
    {
        setTitle("Graph");
        setLocation(430, 60);
        setSize(700,420);
        setResizable(false);
        setVisible(true);

        xArrayNoDrag = xNoDrag;
        yArrayNoDrag = yNoDrag;
        xArrayDrag = xDrag;
        yArrayDrag = yDrag;
        xArrayMagnus = xMagnus;
        yArrayMagnus = yMagnus;
        net = n;
        noDrag = nd;
        drag = d;
        magnus = m;
    }

    public void paint(Graphics g)
    {
        if(net)g.drawLine(350, 280, 350, 300);                      //tennis net
        g.drawLine(50, 300, 650, 300);                              //ground

        g.setColor(Color.BLUE);                                                     //info
        g.drawString("ohne Luftwiderstand", 50, 350);
        g.setColor(Color.RED);
        g.drawString("mit Luftwiderstand", 50, 370);
        g.setColor(Color.ORANGE);
        g.drawString("mit Luftwiderstand und Magnuseffekt", 50, 390);

        if(noDrag)
            paintGraph(g, Color.BLUE, xArrayNoDrag, yArrayNoDrag, 350);
        if(drag)
            paintGraph(g, Color.RED, xArrayDrag, yArrayDrag, 370);
        if(magnus)
            paintGraph(g, Color.ORANGE, xArrayMagnus, yArrayMagnus, 390);
    }

    private void paintGraph(Graphics graphics, Color color, ArrayList<Double> x, ArrayList<Double> y, int height)
    {
        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(0);
        graphics.setColor(color);
        //try
        //{
            for(int i = 0; i < x.size(); i++)
            {
                //Thread.sleep(2);
                int X = Integer.parseInt(n.format(25 * x.get(i)));
                int Y = Integer.parseInt(n.format(25 * y.get(i)));
                graphics.fillOval(X + 49, 298 - Y, 3, 3);
            }
        /*}
        catch (Exception e)
        {
            System.out.print("Error, tried to sleep");
        }*/
        n.setMaximumFractionDigits(2);
        graphics.drawString("Auftreffpunkt: " + n.format(x.get(x.size() - 1)) + "m", 350, height);
    }

}
