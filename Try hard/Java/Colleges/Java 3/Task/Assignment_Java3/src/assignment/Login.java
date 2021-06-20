package assignment;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class Login extends Method {
    JInternalFrame f;
    JPanel panel;
    JLabel lblName, lblPass;
    JTextField txtName;
    JPasswordField passwordField;
    JButton btnLogin, btnCancel;
    GridBagConstraints gbc;

    public void taoDoiTuong() {
        f = new JInternalFrame("Login");
        gbc = new GridBagConstraints();

        panel = new JPanel(new GridBagLayout());
        
        lblName = new JLabel("User name:");
        lblPass = new JLabel("Password:");
        
        txtName = new JTextField(20);
        passwordField = new JPasswordField(20);
        
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        panel.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Login", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("", Font.BOLD, 20), Color.black));
    }
    
    public void add() {
        JComponent mangPanel[] = {lblName, lblPass, txtName, passwordField};
        addChung(panel, gbc, mangPanel, 2, 2, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        gbc.gridy = 2;
        panel.add(btnLogin, gbc);
        
        gbc.insets = new Insets(5, 80, 5, 5);
        panel.add(btnCancel, gbc);
        
        gbc.insets = new Insets(10, 10, 10, 10);
        f.add(panel, gbc);
        
        f.pack();
        f.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        f.setLocation(470, 200);
        f.setVisible(true);
    }
    
    public void event() {
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventLogin();
            }
        });
        
        btnLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    eventLogin();
                }
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventCannel();
            }
        });
        
        btnCancel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    eventCannel();
                }
            }
        });
    }
    
    public void eventLogin() {
        int a = 0;
        try {
            pst = con.prepareStatement("select * from users");
            rs = pst.executeQuery();
            while (rs.next()) {
                if (rs.getString(1).equals(txtName.getText()) && Arrays.equals(rs.getString(2).toCharArray(), passwordField.getPassword())) {
                    switch (rs.getString(3)) {
                        case "admin":
                            try {
                                KhungTo.menuQuanLyDiemSinhVien.setEnabled(true);
                                KhungTo.menuQuanLySinhVien.setEnabled(true);
                                f.setClosed(true);
                            }
                            catch (Exception ex) {
                                System.out.println(ex);
                            }
                            break;
                        case "giang vien":
                            try {
                                KhungTo.menuQuanLyDiemSinhVien.setEnabled(true);
                                KhungTo.menuQuanLySinhVien.setEnabled(false);
                                f.setClosed(true);
                            }
                            catch (Exception ex) {
                                System.out.println(ex);
                            }
                            break;
                        case "can bo dao tao":
                            try {
                                KhungTo.menuQuanLyDiemSinhVien.setEnabled(false);
                                KhungTo.menuQuanLySinhVien.setEnabled(true);
                                f.setClosed(true);
                            }
                            catch (Exception ex) {
                                System.out.println(ex);
                            }
                            break;
                    }
                    a = 0;
                    KhungTo.menuItemExit.setEnabled(true);
                    break;
                }
                else {
                    a = 1;
                }
            }

            if (a == 1) {
                JOptionPane.showMessageDialog(f, "Sai tên đăng nhập hoặc mật khẩu");
            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void eventCannel() {
        try {
            f.setClosed(true);
            KhungTo.menuItemLogin.setEnabled(true);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
