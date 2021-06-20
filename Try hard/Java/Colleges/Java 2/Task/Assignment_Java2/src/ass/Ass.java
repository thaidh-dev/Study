package ass;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import static ass.Employee.*;
import javax.swing.border.*;

public class Ass {
    
    public static void main(String[] args) {
        f = new JFrame("QUẢN LÝ NHÂN VIÊN");
        f.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("", Font.PLAIN, 20);

        JLabel lblTieuDe = new JLabel("QUẢN LÝ NHÂN VIÊN");
        lblTieuDe.setFont(new Font("", Font.BOLD, 40));
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);
        f.add(lblTieuDe, gbc);
        
        JLabel lblMa = new JLabel("MÃ NHÂN VIÊN");
        lblMa.setFont(font);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        f.add(lblMa, gbc);
        
        JLabel lblTen = new JLabel("HỌ VÀ TÊN");
        lblTen.setFont(font);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 5, 5);
        f.add(lblTen, gbc);
        
        JLabel lblTuoi = new JLabel("TUỔI");
        lblTuoi.setFont(font);
        gbc.gridy = 3;
        f.add(lblTuoi, gbc);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(font);
        gbc.gridy = 4;
        f.add(lblEmail, gbc);
        
        JLabel lblLuong = new JLabel("LƯƠNG");
        lblLuong.setFont(font);
        gbc.gridy = 5;
        f.add(lblLuong, gbc);
        
        txtMa = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;
        f.add(txtMa, gbc);
        
        txtTen = new JTextField(30);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 5, 5 ,5);
        f.add(txtTen, gbc);
        
        txtTuoi = new JTextField(10);
        gbc.gridy = 3;
        f.add(txtTuoi, gbc);
        
        txtEmail = new JTextField(30);
        gbc.gridy = 4;
        f.add(txtEmail, gbc);
        
        txtLuong = new JTextField(20);
        gbc.gridy = 5;
        f.add(txtLuong, gbc);
        
        JButton btn1 = new JButton("|<");
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5);
        f.add(btn1, gbc);
        
        JButton btn2 = new JButton("<<");
        gbc.insets = new Insets(5, 60, 5, 5);
        f.add(btn2, gbc);
        
        JButton btn3 = new JButton(">>");
        gbc.insets = new Insets(5, 120, 5, 5);
        f.add(btn3, gbc);
        
        JButton btn4 = new JButton(">|");
        gbc.insets = new Insets(5, 180, 5, 5);
        f.add(btn4, gbc);
        
        lblTrangThai = new JLabel("Record: 0 of 0");
        lblTrangThai.setForeground(Color.red);
        gbc.insets = new Insets(5, 240, 5, 5);
        f.add(lblTrangThai, gbc);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(7, 1, 0, 7));
        p.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 15, 5, 5);
        gbc.gridheight = 7;
        f.add(p, gbc);

        JLabel lblClock = new JLabel();
        lblClock.setForeground(new Color(248, 148, 6));
        p.add(lblClock);
        
        btnNew = new JButton("New");
        p.add(btnNew);
        
        btnSave = new JButton("Save");
        p.add(btnSave);
        
        btnDelete = new JButton("Delete");
        p.add(btnDelete);
        
        btnFind = new JButton("Find");
        p.add(btnFind);
        
        btnOpen = new JButton("Open");
        p.add(btnOpen);
        
        btnExit = new JButton("Exit");
        p.add(btnExit);
        
        Vector tenCot = new Vector();
        tenCot.add("Mã");
        tenCot.add("Họ tên");
        tenCot.add("Tuổi");
        tenCot.add("Email");
        tenCot.add("Lương");
        Vector duLieu = new Vector();
        tbl = new JTable(duLieu, tenCot);
        tbl.setPreferredScrollableViewportSize(new Dimension(600, 100));
        tbl.setFillsViewportHeight(true);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(0);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(0);
        tbl.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(50);
        JScrollPane sp = new JScrollPane(tbl);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 5, 5, 5);
        f.add(sp, gbc);
        
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.moi();
                eventMoi = -1;
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (eventMoi == -1 && tbl.getSelectedRow() == -1) {
                    a.them();
                    a.save();
                }
                else {
                    a.update();
                    a.save();
                }
            }
        });
        
        btnOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.open();
                a.fillToTable();
            }
        });
        
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                a.showDetail();
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.delete();
                a.save();
                a.fillToTable();
            }
        });
        
        btn2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                a.tienLui(-1);
            }
        });
        
        btn3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                a.tienLui(1);
            }
        });
        
        btn1.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               a.dauCuoi(-1);
           }
        });
        
        btn4.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e) {
               a.dauCuoi(1);
           }
        });
        
        btnFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.tim();
            }
        });
        
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                a.open();
                a.fillToTable();
            }
        });
        
        f.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tbl.clearSelection();
            }
        });

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        while (true) {
            Calendar calendar = Calendar.getInstance();
            String hour = calendar.get(Calendar.HOUR) > 9 ? 
                    "" + calendar.get(Calendar.HOUR) 
                    : "0" + calendar.get(Calendar.HOUR);
            String minute = calendar.get(Calendar.MINUTE) > 9 ?
                    "" + calendar.get(Calendar.MINUTE) 
                    : "0" + calendar.get(Calendar.MINUTE);
            String second = calendar.get(Calendar.SECOND) > 9 ?
                    "" + calendar.get(Calendar.SECOND)
                    : "0" + calendar.get(Calendar.SECOND);
            int buoi = calendar.get(Calendar.AM_PM);
            String buoiString = "AM";
            if (buoi == 1) {
                buoiString = "PM";
            }
            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {
            }
            lblClock.setText(hour + ":" + minute + ":" + second + " " + buoiString);
        }
    }
}
