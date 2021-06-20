package assignment;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javazoom.jl.player.Player;

public class KhungTo extends Method {
    JFrame f;
    JMenuBar menuBar;
    static JMenu menuFile, menuQuanLyDiemSinhVien, menuQuanLySinhVien;
    static JMenuItem menuItemLogin, menuItemExit;
    JDesktopPane desktopPane;
    GridBagConstraints gbc;
    JLabel lblBanner;
    
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } 
                catch (Exception ex) {
                    System.out.println(ex);
                }
                KhungTo a = new KhungTo();
                a.taoDoiTuong();
                a.taoDang();
                a.event();
                a.add();
            }
        }.start();
        
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\music.mp3");
                        Player player = new Player(fis);
                        player.play();
                        player.close();
                        fis.close();
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }.start();
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Trường Cao Đẳng FPT");
        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuQuanLySinhVien = new JMenu("Quản lý sinh viên");
        menuQuanLyDiemSinhVien = new JMenu("Quản lý điểm sinh viên");
        desktopPane = new JDesktopPane();
        gbc = new GridBagConstraints();
        menuItemLogin = new JMenuItem("Login");
        menuItemExit = new JMenuItem("Đăng xuất");
        lblBanner = new JLabel();
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        
        desktopPane.setPreferredSize(new Dimension(1300, 700));
        
        lblBanner.setSize(1300, 700);
        lblBanner.setBorder(new BevelBorder(BevelBorder.LOWERED));
        // chỉnh kích thước ảnh
        try {
            BufferedImage bi = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Assignment_Java3\\image\\banner.jpg"));
            ImageIcon imageIcon = new ImageIcon(bi.getScaledInstance(1300, 700, Image.SCALE_DEFAULT));
            lblBanner.setIcon(imageIcon);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        
        menuQuanLyDiemSinhVien.setEnabled(false);
        menuQuanLySinhVien.setEnabled(false);
        
        menuItemLogin.setPreferredSize(new Dimension(150, 30));
        menuItemLogin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        menuItemExit.setPreferredSize(new Dimension(150, 30));
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        menuItemExit.setEnabled(false);
        
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuQuanLyDiemSinhVien.setMnemonic(KeyEvent.VK_D);
        menuQuanLySinhVien.setMnemonic(KeyEvent.VK_S);
    }
    
    public void add() {
        JComponent mangMenuItem[] = {menuItemLogin, menuItemExit};
        addChung(menuFile, null, mangMenuItem, 2, 1, null, 0);
        
        JComponent mangMenuBar[] = {menuFile, menuQuanLyDiemSinhVien, menuQuanLySinhVien};
        addChung(menuBar, null, mangMenuBar, 1, 3, null, 0);
        
        desktopPane.add(lblBanner);

        JComponent mangF[] = {menuBar, desktopPane};
        addChung(f, gbc, mangF, 2, 1, new Insets(0, 0, 0, 0), GridBagConstraints.WEST);

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    public void event() {
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                lienKetSql();
            }
        });
        
        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    QuanLyDiemSinhVien.f.setClosed(true);
                    QuanLySinhVien.f.setClosed(true);
                    menuItemLogin.setEnabled(true);
                    menuQuanLyDiemSinhVien.setEnabled(false);
                    menuQuanLySinhVien.setEnabled(false);
                    menuItemExit.setEnabled(false);
                }
                catch (Exception ex) {
                    try {
                        QuanLySinhVien.f.setClosed(true);
                    }
                    catch (Exception ex2) {
                        System.out.println(ex2);
                    }
                    menuItemLogin.setEnabled(true);
                    menuQuanLyDiemSinhVien.setEnabled(false);
                    menuQuanLySinhVien.setEnabled(false);
                    menuItemExit.setEnabled(false);
                    System.out.println(ex);
                }
            }
        });
        
        menuItemLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.taoDoiTuong();
                login.taoDang();
                login.event();
                login.add();
                desktopPane.add(login.f);
                menuItemLogin.setEnabled(false);
            }
        });

        menuQuanLyDiemSinhVien.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                desktopPane.add(new QuanLyDiemSinhVien().f);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                menuQuanLyDiemSinhVien.setEnabled(false);
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        
        menuQuanLySinhVien.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                desktopPane.add(new QuanLySinhVien().f);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                menuQuanLySinhVien.setEnabled(false);
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
    }
}
