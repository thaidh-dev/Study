import java.io.FileInputStream;
import javax.swing.*;
import javazoom.jl.player.Player;

public class PhatNhac {
    double x;
    double y = 99;
    int z = 1;
    FileInputStream fis;
    Player player;
    JProgressBar prg;

    public PhatNhac() {
        prg = new JProgressBar();
        prg.setStringPainted(true);
        
        JFrame win = new JFrame("Music");
        win.add(prg);

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        fis = new FileInputStream(System.getProperty("user.dir") + "\\music.mp3");
                        x = fis.available();
                        player = new Player(fis);
    
                        player.play();

                        fis.close();
                        player.close();
                        y = 99;
                        z = 1;
                        Thread.sleep(1000);
                        prg.setValue(0);
                    } 
                    catch (Exception ex) {
			JOptionPane.showMessageDialog(win, "Thread1: "+ex);
                    }
                }
            }                
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (fis.available() <= x*(y/100)) {
                            y--;
                            prg.setValue(z);
                            z++;
                        }
                        Thread.sleep(10);
                    } 
                    catch (Exception ex) {
			JOptionPane.showMessageDialog(win, "Thread2: "+ex);
                    }
                }
            }              
        }.start();
            
        win.pack();
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setLocationRelativeTo(null);
        win.setVisible(true);
    }
    
    public static void main(String[] args) {
        new PhatNhac();
    }
}