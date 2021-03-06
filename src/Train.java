//package trains;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;

import java.awt.image.ImageObserver;

import java.util.Random;

import javax.swing.JOptionPane;


public class Train{

    /**
     * @param aCanvas
     * @param c
     * @param initSpeed
     * @param accel
     */
    public Train(Container aCanvas, Color c, int initSpeed, int accel){
        this.setValues(aCanvas, c, initSpeed, accel);
    }

    /**
     * @param aCanvas
     * @param c
     * @param initSpeed
     * @param accel
     * @param trainObj
     */
    public Train(Container aCanvas, Color c, int initSpeed, int accel, Train trainObj){
        this.setValues(aCanvas, c, initSpeed, accel);
        prevTrain = trainObj;
    }

    private void setValues(Container aCanvas, Color c, int initSpeed, int accel){
        drawCanvas = aCanvas;
        tColor = c;
        speed = (double) initSpeed/60;
        dx = (double) initSpeed/60;
        ddx = (double) accel/60;
    }
    
    /**
     * @param g2
     */
    public void draw(Graphics2D g2){
        g2.setColor(tColor);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    /**
     * @param curTThread
     */
    public void move(TThread curTThread){
        distance = distance + dx;
        x = (int) distance % drawCanvas.getWidth();
        y = (int) (distance / drawCanvas.getWidth()) * 20;
        if (prevTrain != null){
            if(getDistance(prevTrain)<(50.0 + XSIZE/2)){
                dx = 0;
                try {
                    Random seed = new Random();
                    curTThread.sleep(seed.nextInt(10001) + 1000);
                    System.out.println(this.tColor + "stopped not to crash " + prevTrain.tColor);
                    dx = speed;
                } catch (InterruptedException e) {
                    JOptionPane.showMessageDialog(null, e, "Exception @ Train.move()", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (getDistance(prevTrain)<(100.0 + XSIZE/2)){
                Random acc = new Random();
                dx = dx + (acc.nextDouble() * ddx) - ddx;
                if (dx < speed - ddx){
                    dx = speed - ddx;
                }
            }
            else{
                //dx += ddx;
                Random acc = new Random();
                dx = dx + (acc.nextDouble() * ddx);
                    if (dx > speed + ddx){
                        dx = speed + ddx;
                    }
            }
        }
                
        drawCanvas.paint(drawCanvas.getGraphics());
    }

    /**
     * @param t
     * @return
     */
    public double getDistance(Train t){
        return t.distance - this.distance;
    }
 	
    private Component drawCanvas;
    private double distance = 0;
    private double x = 0;
    private double y = 0;
    private double speed, dx, ddx;
    private int waitTime;
    private static final int XSIZE = 45;
    private static final int YSIZE = 15;
    private Color tColor;
    private Train prevTrain;
}
