//package trains;

import java.awt.Graphics;

import java.awt.Graphics2D;

import java.util.ArrayList;

import javax.swing.JPanel;

public class TCanvas extends JPanel{

    /**
     * @param t
     */
    @SuppressWarnings("unchecked")
    public void add(Train t){
	trains.add(t);
    }

    /**
     * @param trainGraphic
     */
    public void paintComponent(Graphics trainGraphic){
	super.paintComponent(trainGraphic);
	Graphics2D g2 = (Graphics2D)trainGraphic;
	for (int i = 0; i < trains.size(); i++){
            Train t = (Train)trains.get(i);
            t.draw(g2);
        }
    }

    /**
     * @return
     */
    public int getTrainsNum(){
        return trains.size();
    }

    /**
     * @param i
     * @return
     */
    public Train getTrain(int i){
        return (Train) trains.get(i);
    }
	
    private ArrayList trains = new ArrayList();
}
