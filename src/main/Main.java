package main;


import java.util.ArrayList;

public class Main {

    private ArrayList<Double>  xArrayNoDrag, yArrayNoDrag, xArrayDrag, yArrayDrag, xArrayMagnus, yArrayMagnus;
    private double g, timestep, rho, m, cw, A, r, k, j, cm;

    Main()
    {
        xArrayNoDrag = new ArrayList<>();
        yArrayNoDrag = new ArrayList<>();

        xArrayDrag = new ArrayList<>();
        yArrayDrag = new ArrayList<>();

        xArrayMagnus = new ArrayList<>();
        yArrayMagnus = new ArrayList<>();

        g = 9.81;
        timestep = 0.001;
        rho = 1.23;
        m = 0.0577;
        cw = 0.35;
        r = 3.255 *  Math.pow(10, -2);
        A = Math.PI * r * r;
        cm = 0.35;

        k = 0.5 * rho * cw * A / m;
        j = 0.5 * rho * cm * A * r / m;

        System.out.println(k);

    }

    public static void main(String[] args)
    {
        GuiMain.main(args);
    }


    void receiveVariables(double velocity, double angle, double height, double distance, double rotationspeed, boolean net, boolean noDrag, boolean drag, boolean magnus)
    {
        if(noDrag)
        {
            xArrayNoDrag.clear();
            yArrayNoDrag.clear();

            double x = distance;
            double y = height;
            double t = 0;

            while(y >= 0 && x < 24)
            {
                xArrayNoDrag.add(x);
                yArrayNoDrag.add(y);

                t += timestep;
                x = velocity*Math.cos(Math.toRadians(angle))*t;
                y = height + velocity*Math.sin(Math.toRadians(angle))*t - 0.5* g *t*t;
            }
        }

        if(drag)
        {
            xArrayDrag.clear();
            yArrayDrag.clear();

            double xOld = distance;
            double yOld = height;
            double vXOld = velocity * Math.cos(Math.toRadians(angle));
            double vYOld = velocity * Math.sin(Math.toRadians(angle));

            double xNew, yNew, aX, aY, vXNew, vYNew, vOld;



            while(yOld >= 0 && xOld < 24)
            {
                xArrayDrag.add(xOld);
                yArrayDrag.add(yOld);


                vOld = Math.sqrt(Math.pow(vXOld, 2) + Math.pow(vYOld, 2));

                aX = - k * vOld * vXOld;
                vXNew = vXOld + aX * timestep;
                xNew = xOld + vXNew * timestep;

                aY = - g - k * vOld * vYOld;
                vYNew = vYOld + aY * timestep;
                yNew = yOld + vYNew * timestep;

                xOld = xNew;
                yOld = yNew;
                vXOld = vXNew;
                vYOld = vYNew;
            }
        }

        if(magnus)
        {
            xArrayMagnus.clear();
            yArrayMagnus.clear();

            double xOld = distance;
            double yOld = height;
            double vXOld = velocity * Math.cos(Math.toRadians(angle));
            double vYOld = velocity * Math.sin(Math.toRadians(angle));

            double xNew, yNew, aX, aY, vXNew, vYNew, vOld;

            while(yOld >= 0 && xOld < 24)
            {
                xArrayMagnus.add(xOld);
                yArrayMagnus.add(yOld);

                vOld = Math.sqrt(Math.pow(vXOld, 2) + Math.pow(vYOld, 2));

                aX = - k * vOld * vXOld - j * rotationspeed * vYOld;
                vXNew = vXOld + aX * timestep;
                xNew = xOld + vXNew * timestep;

                aY = - g - k * vOld * vYOld + j * rotationspeed * vXOld;
                vYNew = vYOld + aY * timestep;
                yNew = yOld + vYNew * timestep;

                xOld = xNew;
                yOld = yNew;
                vXOld = vXNew;
                vYOld = vYNew;
            }
        }

        new GuiGraph(xArrayNoDrag, yArrayNoDrag, xArrayDrag, yArrayDrag, xArrayMagnus, yArrayMagnus, net, noDrag, drag, magnus);

    }
}
