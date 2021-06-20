package lab.pkg5;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import javax.swing.table.*;
import java.awt.event.*;


public class GiaoDien {
    
    static JTextField txtTen, txtLuong;
    static JTable tbl;
    static GiaoDien a = new GiaoDien();
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Quản lý nhân viên");
        f.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("", Font.BOLD, 20);
        
        JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTieuDe.setFont(new Font("", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.red);
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        f.add(lblTieuDe, gbc);
        
        JLabel lblTen = new JLabel("HỌ VÀ TÊN");
        lblTen.setFont(font);
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        f.add(lblTen, gbc);
        
        JLabel lblLuong = new JLabel("LƯƠNG");
        lblLuong.setFont(font);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 5, 5);
        f.add(lblLuong, gbc);
        
        txtTen = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 30, 5, 5);
        f.add(txtTen, gbc);
        
        txtLuong = new JTextField(30);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 30, 5, 5);
        f.add(txtLuong, gbc);
        
        JButton btnThem = new JButton("Thêm");
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 30, 5, 5);
        f.add(btnThem, gbc);
        
        JButton btnLuu = new JButton("Lưu");
        gbc.insets = new Insets(5, 110, 5, 5);
        f.add(btnLuu, gbc);
        
        JButton btnDoc = new JButton("Đọc");
        gbc.insets = new Insets(5, 180, 5, 5);
        f.add(btnDoc, gbc);
        
        Vector tenCot = new Vector();
        tenCot.add("Họ tên");
        tenCot.add("Lương");
        Vector duLieu = new Vector();
        tbl = new JTable(duLieu, tenCot);
        tbl.setFillsViewportHeight(true);
        tbl.setPreferredScrollableViewportSize(new Dimension(480, 100));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        JScrollPane sp = new JScrollPane(tbl);
        f.add(sp, gbc);
        
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.them();
                a.fillToTable();
            }
        });
        
        
        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.luu();
            }
        });
        
        btnDoc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.doc();
                a.fillToTable();
            }
        });

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    List <Staff> list = new ArrayList<>();
    
    public void them() {
        Staff nv = new Staff();
        nv.ten = txtTen.getText();
        nv.luong = Double.parseDouble(txtLuong.getText());
        list.add(nv);
    }
    
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        
        for (Staff nv : list) {
            Object[] row = new Object[] {nv.ten, nv.luong};
            model.addRow(row);
        }
    }
    
    private void luu() {
        XFile.writeObject("hello.dat", list);
    }
    
    private void doc() {
        list = (List <Staff>) XFile.readObject("hello.dat");
    }

}
