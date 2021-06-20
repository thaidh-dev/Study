package lab.pkg3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class Bai1 {
    List <Student> list = new ArrayList<>();
    static JTextField txtTen, txtDiem, txtHocLuc;
    static JComboBox cboNganh;
    static JCheckBox chkThuong;
    static JTable tbl;
    static String nganh[] = {"", "Thiết kế, lập trình Website", "Ứng dụng phần mềm", "Lập trình di động", "Thiết kế đồ họa"};
    static JButton btnNhapMoi;
    static Bai1 a = new Bai1();
    static DefaultTableModel model;
    
    public static void main(String[] args) {
        
        JFrame f = new JFrame();
        f.setTitle("QUẢN LÝ SINH VIÊN");
        f.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel lblTieuDe = new JLabel("QUẢN LÝ SINH VIÊN");
        lblTieuDe.setFont(new Font("", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.red);
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5,5,5,5);
        f.add(lblTieuDe, gbc);
        
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        
        Vector tenCot = new Vector();
        tenCot.add("Họ tên");
        tenCot.add("Điểm");
        tenCot.add("Ngành");
        tenCot.add("Học lực");
        tenCot.add("Thưởng");
        model = new DefaultTableModel(tenCot, 7);
        tbl = new JTable(model);
        tbl.getColumnModel().getColumn(0).setPreferredWidth(200);
        tbl.getColumnModel().getColumn(1).setPreferredWidth(20);
        tbl.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbl.getColumnModel().getColumn(4).setPreferredWidth(40);
        gbc.insets = new Insets(10, 10, 5, 5);
        gbc.gridy = 7;
        tbl.setPreferredScrollableViewportSize(new Dimension(660, 100));
        tbl.setFillsViewportHeight(true);
        JScrollPane sp = new JScrollPane(tbl);
        f.add(sp, gbc);

        JLabel lblTen = new JLabel("HỌ TÊN");
        lblTen.setFont(new Font("", Font.BOLD, 20));
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 5, 5);
        f.add(lblTen, gbc);
        
        JLabel lblDiem = new JLabel("ĐIỂM");
        lblDiem.setFont(new Font("", Font.BOLD, 20));
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 5, 5);
        f.add(lblDiem, gbc);

        JLabel lblNganh = new JLabel("NGÀNH");
        lblNganh.setFont(new Font("", Font.BOLD, 20));
        gbc.gridy = 3;
        f.add(lblNganh, gbc);

        JLabel lblHocLuc = new JLabel("HỌC LỰC");
        lblHocLuc.setFont(new Font("", Font.BOLD, 20));
        gbc.gridy = 4;
        f.add(lblHocLuc, gbc);

        gbc.gridx = 1;
        
        txtTen = new JTextField(50);
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 5, 5);
        f.add(txtTen, gbc);
        
        txtDiem = new JTextField(50);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 10, 5, 5);
        f.add(txtDiem, gbc);
        
        cboNganh = new JComboBox(nganh);
        gbc.gridy = 3;
        cboNganh.setEditable(true);
        f.add(cboNganh, gbc);
        
        txtHocLuc = new JTextField(50);
        gbc.gridy = 4;
        txtHocLuc.setEditable(false);
        f.add(txtHocLuc, gbc);
        
        chkThuong = new JCheckBox("Có phần thưởng?");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 10, 5, 5);
        f.add(chkThuong, gbc);
        
        gbc.gridy = 6;
        
        JButton btnThem = new JButton("Thêm");
        gbc.insets = new Insets(5, 10, 5, 5);
        f.add(btnThem, gbc);
        
        JButton btnXoa = new JButton("Xóa");
        gbc.insets = new Insets(5, 90, 5, 5);
        f.add(btnXoa, gbc);
        
        JButton btnCapNhat = new JButton("Cập nhật");
        gbc.insets = new Insets(5, 160, 5, 5);
        f.add(btnCapNhat, gbc);
        
        btnNhapMoi = new JButton("Nhập mới");
        gbc.insets = new Insets(5, 259, 5, 5);
        f.add(btnNhapMoi, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        JButton btnXepTen = new JButton("Sắp xếp theo tên");
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 5, 5);
        f.add(btnXepTen, gbc);
        
        JButton btnXepDiem = new JButton("Sắp xếp theo điểm");
        gbc.insets = new Insets(5, 150, 5, 5);
        f.add(btnXepDiem, gbc);
        
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.them();
                a.fillToTable();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.xoa();
                a.fillToTable();
           }
        });
        
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.update();
                a.fillToTable();
           }
        });
        
        btnNhapMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.NhapMoi();
            }
        });
        
        tbl.addMouseListener(new MouseAdapter() {
            /* Vì MouseAdapter là 1 class trừu tượng không phương thức nên không cần Override toàn bộ method như interface */
            @Override
            public void mouseClicked(MouseEvent e) {
                a.showDetail();
            }
        });
        
        btnXepTen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.xepTen();
                a.fillToTable();
            }
        });
        
        btnXepDiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.xepDiem();
                a.fillToTable();
            }
        });
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void them() {
        /* Đưa vào list, vì list có kiểu dữ liệu là tham chiếu của Student*/
        Student sv = new Student();
        sv.ten = txtTen.getText();
        sv.diem = Double.parseDouble(txtDiem.getText());
        sv.nganh = (String) cboNganh.getSelectedItem();
        list.add(sv);
        /* bấm thêm và hiện học lực và tích v nếu có thưởng */
        txtHocLuc.setText(sv.hocLuc()); 
        chkThuong.setSelected(sv.thuong());
    }
    
    public void fillToTable() {
        model.setRowCount(0);
        for (Student sv : list) {
            Object[] row = {sv.ten, sv.diem, sv.nganh, sv.hocLuc(), sv.thuong()};
            model.addRow(row);
        }
    }
    
    public void showDetail() {
        /* String.valueOf(sv.diem); chuyển double thành chuỗi*/
        int index = tbl.getSelectedRow();
        Student sv = list.get(index);
        txtTen.setText(sv.ten);
        txtDiem.setText(String.valueOf(sv.diem));
        cboNganh.setSelectedItem(sv.nganh);
        txtHocLuc.setText(sv.hocLuc());
        chkThuong.setSelected(sv.thuong());
    }
    
    public void xoa() {
        int index = tbl.getSelectedRow();
        list.remove(index);
    }
    
    public void update() {
        int index = tbl.getSelectedRow();
        Student sv = list.get(index);
        
        sv.ten = txtTen.getText();
        sv.diem = Double.parseDouble(txtDiem.getText());
        sv.nganh = (String) cboNganh.getSelectedItem();
        txtHocLuc.setText(sv.hocLuc());
        chkThuong.setSelected(sv.thuong());
    }
    
    public void NhapMoi() {
        txtTen.setText("");
        txtDiem.setText("");
        txtHocLuc.setText("");
        cboNganh.setSelectedItem(nganh[0]);
        chkThuong.setSelected(false);
    }
    
    public void xepTen() {
        Comparator <Student> com = new Comparator <Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.ten.compareTo(o2.ten);
            }
        };
        Collections.sort(list, com);
    }
    
    public void xepDiem() {
        Comparator <Student> com = new Comparator <Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                Double a = o1.diem;
                Double b = o2.diem;
                /* Double.valueOf(String.valueOf(o1.diem)).compareTo(b);
                chuyển o1.diem thành kiểu đối tượng Double vì 
                method compareTo nó bắt phải dùng kiểu dữ liệu đối tượng Double
                */
                return a.compareTo(b);
            }
        };
        Collections.sort(list, com);
    }
}
