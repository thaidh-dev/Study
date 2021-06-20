package lab.pkg3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;

public class Bai2 {
    JFrame f;
    JPanel p1, p2, p3, p4, pSoThich;
    JLabel lblHo, lblTen, lblBiDanh, lblMatMa, lblQue, lblSoThich, lblGhiChu;
    JButton btnOk, btnReset, btnExit;
    JTextField txtHo, txtBiDanh, txtTen;
    JPasswordField pfMatMa;
    JRadioButton radNam, radNu, radKhong;
    JComboBox cboQue;
    JCheckBox chkAn, chkNhay, chkNgu;
    JTextArea txaGhiChu;
    String que[] = {"Ninh Thuận", "Hà Nội", "Hải Dương", "Nam Định", "Đồng Nai", "Bình Định", "Cà Mau"};
    JScrollPane sp;
    ButtonGroup bg;
    GridBagConstraints gbc;
    
    public static void main(String[] args) {
        Bai2 a = new Bai2();
        a.taoDoiTuong();
        a.taoDang();
        a.add();
        a.event();
    }
    
    public void add() {
        JComponent mangP1[] = {lblHo, txtHo, lblTen, txtTen, lblBiDanh, txtBiDanh, lblMatMa, pfMatMa};
        addChung(p1, gbc, mangP1, 2, 4, new Insets(5, 5, 5, 5), GridBagConstraints.EAST);
        
        bg.add(radNam);
        bg.add(radNu);
        bg.add(radKhong);
        
        JComponent mangP2[] = {radNam, radNu, radKhong};
        addChung(p2, null, mangP2, 1, 3, null, 0);
        
        JComponent mangSoThich[] = {chkAn, chkNhay, chkNgu};
        addChung(pSoThich, null, mangSoThich, 1, 3, null, 0);
        
        JComponent mangP3[] = {lblQue, cboQue, lblSoThich, pSoThich, lblGhiChu, sp};
        addChung(p3, gbc, mangP3, 3, 2, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        JComponent mangP4[] = {btnOk, btnReset, btnExit};
        addChung(p4, null, mangP4, 1, 3, null, 0);

        JComponent mangF[] = {p1, p2, p3, p4};
        addChung(f, gbc, mangF, 4, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Bài 2");
        bg = new ButtonGroup();
        txaGhiChu = new JTextArea(5, 40);
        sp = new JScrollPane(txaGhiChu);
        gbc = new GridBagConstraints();

        p1 = new JPanel(new GridBagLayout());
        p2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 15));
        p3 = new JPanel(new GridBagLayout());
        p4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        pSoThich = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        
        lblHo = new JLabel("Họ");
        lblBiDanh = new JLabel("Bí danh");
        lblTen = new JLabel("Tên");
        lblMatMa = new JLabel("Mật mã");
        lblQue = new JLabel("Quê quán");
        lblSoThich = new JLabel("Sở thích");
        lblGhiChu = new JLabel("Ghi chú");

        txtHo = new JTextField(20);
        txtBiDanh = new JTextField(15);
        txtTen = new JTextField(30);

        pfMatMa = new JPasswordField(15);

        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");
        radKhong = new JRadioButton("Không xác định");

        cboQue = new JComboBox(que);
        cboQue.setPreferredSize(new Dimension(150, 20));

        chkAn = new JCheckBox("Ăn chơi");
        chkNhay = new JCheckBox("Nhảy múa");
        chkNgu = new JCheckBox("Ngủ nghỉ");

        btnOk = new JButton("OK");
        btnReset = new JButton("Reset");
        btnExit = new JButton("Exit");
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        p1.setBackground(Color.GREEN);
        p1.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Họ tên", TitledBorder.CENTER, 2, new Font("tahoma", Font.PLAIN, 20), Color.red));

        p2.setPreferredSize(new Dimension(690, 70));
        p2.setBackground(Color.yellow);
        p2.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Giới tính", 0, 0, new Font("tahoma", Font.PLAIN, 20), Color.blue));

        p3.setPreferredSize(new Dimension(690, 200));
        p3.setBackground(Color.pink);
        p3.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Thông tin khác", 0, 0, new Font("tahoma", Font.PLAIN, 20), Color.black));

        p4.setPreferredSize(new Dimension(690, 50));
    }
    
    public void event() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtHo.setText("");
                txtTen.setText("");
                txtBiDanh.setText("");
                pfMatMa.setText("");
                bg.clearSelection();
                cboQue.setSelectedIndex(0);
                chkAn.setSelected(false);
                chkNhay.setSelected(false);
                chkNgu.setSelected(false);
                txaGhiChu.setText("");
            }
        });
        
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "Họ tên: " + txtHo.getText() + " " + txtTen.getText() +
                                                    "\nBí danh: " + txtBiDanh.getText() +
                                                    "\nGiới tính: " + (radNam.isSelected() ? "Nam" : radNu.isSelected() ? "Nữ" : "Không xác định") +
                                                    "\nQuê quán: " + cboQue.getSelectedItem() +
                                                    "\nSở thích: " + (chkAn.isSelected() ? "Ăn chơi, " : "") + (chkNhay.isSelected() ? "Nhảy múa, " : "") + (chkNgu.isSelected() ? "Ngủ nghỉ" : "") +
                                                    "\nGhi chú: " + txaGhiChu.getText()
                );
            }
        });
    }
    
    public void addChung(Container c, GridBagConstraints gbc, JComponent mang[], int row, int col, Insets insets, int anchor) {
        int z = 0;
        if (gbc == null) {
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    c.add(mang[x]);
                    z++;
                }
            }
        }
        else {
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    gbc.gridx = x;
                    gbc.gridy = y;
                    gbc.insets = insets;
                    gbc.anchor = anchor;
                    c.add(mang[z], gbc);
                    z++;
                }
            }
        }
    }
} 
