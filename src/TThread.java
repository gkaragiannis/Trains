//package trains;

import javax.swing.JOptionPane;

public class TThread extends Thread{
    /**
     * @param objTrain
     * @param wait
     */
    public TThread(Train objTrain, boolean wait) {
        t = objTrain;
        if (wait){
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, e, "Exception @ TThread.TThread()", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void run(){
        try{
            while (true){
                t.move(getCurrentTThread());
                sleep(2);
            }
        }
        catch (InterruptedException e){
            JOptionPane.showMessageDialog(null, e, "Exception @ TThread.run()", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * @return
     */
    public TThread getCurrentTThread(){
        return (TThread) this.currentThread();
    }
    
    private Train t;
}
