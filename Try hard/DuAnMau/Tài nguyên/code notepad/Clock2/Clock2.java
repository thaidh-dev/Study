import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class Clock2 {
    public static void main(String[] args) {
        JFrame win = new JFrame("Clock");
        JLabel lbl = new JLabel();
        SimpleDateFormat format = new SimpleDateFormat("hh-mm-ss");
        
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Date now = new Date();
                        String ngayLucNay = format.format(now);
                        lbl.setText(ngayLucNay);
                        Thread.sleep(1000);
                    } 
                    catch (InterruptedException ex) {
                    }
                }
            }
        }.start();
        
        win.add(lbl);
        
        win.setSize(300, 300);
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}