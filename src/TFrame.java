//package trains;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TFrame extends JFrame {
    public TFrame(){
	
        // get screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	int screenHeight = screenSize.height;
	int screenWidth = screenSize.width;
	
	// center frame in screen
	setSize(screenWidth, screenHeight);
	setLocation(0,0);
	setResizable(true);
	                
	// set frame icon and title
	Image img = kit.getImage(getClass().getResource("train_right.gif"));
	setIconImage(img);
	setTitle("Trains - Project 2");
        	
	/*
            source: https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
            "...JFrame contains a JRootPane as its only child. The content pane provided by the root pane
            should, as a rule, contain all the non-menu components displayed by the JFrame. This is 
            different from the AWT Frame case. As a conveniance add and its variants, remove and setLayout 
            have been overridden to forward to the contentPane as necessary..."
 	*/
 	Container contentPane = getContentPane();
		
	mainCanvas = new TCanvas();
	contentPane.add(mainCanvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
 	addButton(
            buttonPanel, 
            "Start", 
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    addTrain();
                }
            }
 	);
 	addButton(
            buttonPanel, 
            "Stop", 
            new ActionListener(){
                public void actionPerformed(ActionEvent ev){
                    System.exit(0);
                }
            }
 	);
 		
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public void addTrain(){

        while (mainCanvas.getTrainsNum() < 4){
        
            if (mainCanvas.getTrainsNum() == 0){
                newClr = Color.RED;
                speed = 120;
                acceleration = 0;
                
                Train t = new Train(mainCanvas, newClr, speed, acceleration);
                mainCanvas.add(t);
                TThread newTrainThread = new TThread(t, false);
                newTrainThread.start();
            }
            else{
                if (mainCanvas.getTrainsNum() == 1){
                    newClr = Color.GREEN;
                    speed = 110;
                    acceleration = 20;
                }
                else if (mainCanvas.getTrainsNum() == 2){
                    newClr = Color.YELLOW;
                    speed = 100;
                    acceleration = 40;
                }
                else if (mainCanvas.getTrainsNum() == 3){
                    newClr = Color.BLUE;
                    speed = 90;
                    acceleration = 60;
                }
        
                Train t = new Train(mainCanvas, newClr, speed, acceleration, mainCanvas.getTrain(mainCanvas.getTrainsNum() - 1));
                mainCanvas.add(t);
                TThread newTrainThread = new TThread(t, true);
                newTrainThread.start();
            }
        }
    }

    /**
     * @param pane
     * @param title
     * @param listener
     */
    public void addButton(Container pane, String title, ActionListener listener){
 	JButton newButtton = new JButton(title);
 	pane.add(newButtton);
        newButtton.addActionListener(listener);
    }
 	
    private TCanvas mainCanvas;
    private Color newClr;
    private int speed;
    private int acceleration;
}
