package lab.pkg5;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class Bai4 {
    static JTextField txtTen;
    static JTextArea txtND;
    static JOptionPane op = new JOptionPane();
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Đọc/ghi file văn bản");
        f.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel lblTen = new JLabel("Tên file");
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        f.add(lblTen, gbc);
        
        JLabel lblNoiDung = new JLabel("Nội dung");
        gbc.gridy = 1;
        f.add(lblNoiDung, gbc);
        
        txtTen = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 0;
        f.add(txtTen, gbc);
        
        txtND = new JTextArea(10, 36);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        f.add(txtND, gbc);
        
        JButton btnDoc = new JButton("Đọc");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        f.add(btnDoc, gbc);
        
        JButton btnLuu = new JButton("Lưu");
        gbc.insets = new Insets(5, 0, 5, 70);
        f.add(btnLuu, gbc);
        
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Luu();
            }
        });
        
        btnDoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Doc();
            }
        });
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public static void Luu() {
        String path = txtTen.getText();
        String nd = txtND.getText();
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            bw.write(nd);
            bw.newLine();
            bw.close();
            JOptionPane.showMessageDialog(op, "Ghi file thành công");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(op, "Lỗi ghi file");
        }
    }
    
    public static void Doc() {
        txtND.setText("");
        String path = txtTen.getText();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                else {
                    txtND.append(line);
                    txtND.append("\r\n");
                }
            }
            br.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(op, "Lỗi đọc file");
        }
    }
}
