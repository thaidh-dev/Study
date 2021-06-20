package lab.pkg2;

import java.awt.BorderLayout;
import javax.swing.*;

public class Chao extends Khung {
    JDialog chaoJDialog;
    JLabel lblLogo;
    JProgressBar progressBar;

    public Chao() {
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    public static void main(String[] args) {
        new Chao();
    }
    
    @Override
    public void taoDoiTuong() {
        chaoJDialog = new JDialog();
        lblLogo = new JLabel(new ImageIcon(System.getProperty("user.dir") + "\\Hinh\\logo.png"));
        progressBar = new JProgressBar();
    }
    
    @Override
    public void taoDang() {
        chaoJDialog.setLayout(new BorderLayout());
        chaoJDialog.setUndecorated(true);
        
        progressBar.setStringPainted(true);
    }
    
    @Override
    public void add() {
        chaoJDialog.add(lblLogo, BorderLayout.CENTER);
        chaoJDialog.add(progressBar, BorderLayout.SOUTH);
        
        chaoJDialog.pack();
        chaoJDialog.setLocationRelativeTo(null);
        chaoJDialog.setVisible(true);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }
    }
    
    @Override
    public void event() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    progressBar.setValue(i);
                    try {
                        Thread.sleep(10);
                    }
                    catch (Exception e) {
                    }
                }
                chaoJDialog.dispose();
                new DangNhap();
            }
        }.start();
    }
}
