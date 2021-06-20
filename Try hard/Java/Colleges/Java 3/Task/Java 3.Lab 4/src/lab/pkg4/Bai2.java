package lab.pkg4;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class Bai2 {
    JFrame f;
    JPanel panelTop, panelMid, panelBot;
    JTable table;
    JLabel lblMa, lblTen, lblDonVi, lblGia, lblNhaCungCap;
    JTextField txtMa, txtTen, txtGia, txtNhaCungCap, txtCbo;
    JButton btnThem, btnXoa, btnSua;
    JComboBox comboBoxDonVi;
    JScrollPane scrollPane;
    Object tenCot[] = {"Mã SP", "Tên SP", "DVT", "Đơn giá bán", "Nhà cung cấp"};
    String tenDonVi[] = {"Thùng", "Chai", "Lít", "Kg", "Lon", "Lọ", "Chiếc", "Cái", "Km", "Tb"};
    DefaultTableModel model;
    GridBagConstraints gbc;
    String regexGia = "\\d+";
    List<Object[]> list = new ArrayList<>();
    
    public static void main(String[] args) {
        Bai2 a = new Bai2();
        a.taoDoiTuong();
        a.taoDang();
        a.add();
        a.event();
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Bài 2");
        gbc = new GridBagConstraints();
        
        panelTop = new JPanel();
        model = new DefaultTableModel(tenCot, 0);
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        
        panelMid = new JPanel(new GridBagLayout());
        lblMa = new JLabel("Mã sản phẩm");
        lblTen = new JLabel("Tên sản phẩm");
        lblDonVi = new JLabel("Đơn vị tính");
        lblGia = new JLabel("Đơn giá");
        lblNhaCungCap = new JLabel("Nhà cung cấp");
        
        txtMa = new JTextField(20);
        txtTen = new JTextField(20);
        txtNhaCungCap = new JTextField(20);
        txtGia = new JTextField(20);
        comboBoxDonVi = new JComboBox(tenDonVi);
        
        panelBot = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm sản phẩm");
        btnXoa = new JButton("Xóa sản phẩm");
        btnSua = new JButton("Điều chỉnh thông tin");
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(700, 150));
        table.getColumnModel().getColumn(0).setPreferredWidth(0);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(0);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        
        panelTop.setBackground(Color.pink);
        panelTop.setBorder(new TitledBorder(new LineBorder(Color.white, 1, true), "DANH SÁCH SẢN PHẨM", TitledBorder.CENTER, TitledBorder.CENTER, new Font("Tahoma", Font.BOLD, 30), Color.red));
        
        panelMid.setBackground(Color.LIGHT_GRAY);
        panelMid.setPreferredSize(new Dimension(720, 200));
        JComponent mangTxt[] = {txtMa, txtTen, txtGia, txtNhaCungCap, comboBoxDonVi};
        for (int i = 0; i < mangTxt.length; i++) {
            mangTxt[i].setForeground(Color.blue);
        }
        comboBoxDonVi.setPreferredSize((txtMa.getPreferredSize()));
        comboBoxDonVi.setMaximumRowCount(7);
        comboBoxDonVi.setEditable(true);
        comboBoxDonVi.setSelectedItem("");
        txtCbo = (JTextField) comboBoxDonVi.getEditor().getEditorComponent();
        
        panelBot.setBackground(Color.blue);
        panelBot.setPreferredSize(new Dimension(720, 45));
    }
    
    public void add() {
        panelTop.add(scrollPane);
        
        JComponent mangPanelMid[] = {lblMa, txtMa, lblGia, txtGia, lblTen, txtTen, lblNhaCungCap, txtNhaCungCap};
        addChung(panelMid, gbc, mangPanelMid, 2, 4, new Insets(10, 10, 10, 10), GridBagConstraints.EAST);
        gbc.gridy = 2;
        gbc.gridx = 0;
        panelMid.add(lblDonVi, gbc);
        gbc.gridx = 1;
        panelMid.add(comboBoxDonVi, gbc);
        
        JComponent mangPanelBot[] = {btnThem, btnXoa, btnSua};
        addChung(panelBot, null, mangPanelBot, 1, 3, null, 0);
        
        JComponent mangF[] = {panelTop, panelMid, panelBot};
        addChung(f, gbc, mangF, 3, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    public void event() {
        txtCbo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < tenDonVi.length; i++) {
                    if (tenDonVi[i].startsWith(txtCbo.getText())) {
                        list.add(tenDonVi[i]);
                    }
                }
                
                if (list.size() > 0) {
                    String a = txtCbo.getText();
                    comboBoxDonVi.setModel(new DefaultComboBoxModel(list.toArray()));
                    comboBoxDonVi.setSelectedItem(a);
                    comboBoxDonVi.showPopup();
                }
                else {
                    comboBoxDonVi.hidePopup();
                }
            }
        });
        
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                them();
                save();
                fillToTable();
            }
        });
        
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                save();
                fillToTable();
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
                save();
                fillToTable();
            }
        });
        
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                open();
                fillToTable();
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showDetail();
            }
        });
        
        clearSelect(panelTop);
        clearSelect(panelMid);
        clearSelect(panelBot);
        clearSelect(f);
    }
    
    public void them() {
        int trong = 0;
        int sai = 0;
        Object mang[] = new Object[5];
        JTextField mangTxt[] = {txtMa, txtTen, txtGia, txtNhaCungCap};
        
        for (int i = 0; i < mangTxt.length; i++) {
            if (mangTxt[i].getText().isEmpty()) {
                trong++;
            }
        }
        
        if (!txtGia.getText().matches(regexGia)) {
            sai++;
            JOptionPane.showMessageDialog(f, "Mời bạn nhập lại giá");
        }
        
        if (trong > 0) {
            JOptionPane.showMessageDialog(f, "Không để trống ô");
        }
        
        if (trong == 0 && sai == 0) {
            mang[0] = txtMa.getText();
            mang[1] = txtTen.getText();
            mang[2] = comboBoxDonVi.getSelectedItem();
            mang[3] = txtGia.getText();
            mang[4] = txtNhaCungCap.getText();
            list.add(mang);
        }
    }
    
    public void update() {
        try {
            int index = table.getSelectedRow();
            int trong = 0;
            int sai = 0;
            Object mang[] = new Object[5];
            JTextField mangTxt[] = {txtMa, txtTen, txtGia, txtNhaCungCap};

            for (int i = 0; i < mangTxt.length; i++) {
                if (mangTxt[i].getText().isEmpty()) {
                    trong++;
                }
            }

            if (!txtGia.getText().matches(regexGia)) {
                sai++;
                JOptionPane.showMessageDialog(f, "Mời bạn nhập lại giá");
            }
            
            if (trong > 0) {
                JOptionPane.showMessageDialog(f, "Không để trống ô");
            }
            
            if (trong == 0 && sai == 0) {
                mang[0] = txtMa.getText();
                mang[1] = txtTen.getText();
                mang[2] = comboBoxDonVi.getSelectedItem();
                mang[3] = txtGia.getText();
                mang[4] = txtNhaCungCap.getText();
                list.set(index, mang);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng trong bảng trước");
        }
    }
    
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("bai2.txt"));
            oos.writeObject(list);
            oos.close();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Lỗi ghi file");
        }
    }
    
    public void open() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("bai2.txt"));
            list = (List<Object[]>) ois.readObject();
            ois.close();
        } 
        catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Không có gì để mở");
        }
    }
    
    public void fillToTable() {
        model.setRowCount(0);
        for (Object[] a : list) {
            model.addRow(a);
        }
    }
    
    public void showDetail() {
        try {
            int index = table.getSelectedRow();
            Object mang[] = list.get(index);
            txtMa.setText(""+mang[0]);
            txtTen.setText(""+mang[1]);
            comboBoxDonVi.setSelectedItem(""+mang[2]);
            txtGia.setText(""+mang[3]);
            txtNhaCungCap.setText(""+mang[4]);
            
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng trong bảng trước");
        }
    }
    
    public void delete() {
        try {
            int index = table.getSelectedRow();
            list.remove(index);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng trong bảng trước");
        }
    }
    
    public void clearSelect(Container component) {
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                table.clearSelection();
            }
        });
    }
    
    public void addChung(Container c, GridBagConstraints gbc, JComponent mang[], int row, int col, Insets insets, int anchor) {
        int z = 0;
        if (gbc == null) {
            for (int y = 0; y < row; y++) {
                for (int x = 0; x < col; x++) {
                    c.add(mang[z]);
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
