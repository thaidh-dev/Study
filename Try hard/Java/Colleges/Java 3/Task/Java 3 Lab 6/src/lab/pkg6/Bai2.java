package lab.pkg6;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

public class Bai2 extends Student{
    public static void main(String[] args) {
        Bai2 a = new Bai2();
        a.taoDoiTuong();
        a.taoDang();
        a.add();
        a.event();
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Bài 1");
        gbc = new GridBagConstraints();

        model = new DefaultTableModel(tenCot, 0);
        table = new JTable(model);
        scrollPaneTable = new JScrollPane(table);
        
        textArea = new JTextArea();
        scrollPaneTextArea = new JScrollPane(textArea);

        lblName = new JLabel("Name");
        lblAddress = new JLabel("Address");
        lblParentName = new JLabel("ParentName");
        lblContactNo = new JLabel("ContactNo");
        lblStandard = new JLabel("Standard");
        
        txtName = new JTextField(20);
        txtParentName = new JTextField(20);
        txtContactNo = new JTextField(20);
        
        cboStandard = new JComboBox();
        
        btnNew = new JButton("New");
        btnNext = new JButton("Next");
        btnInsert = new JButton("Insert");
        btnPrevious = new JButton("Previous");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");
        btnExit = new JButton("Exit");
        
        panelTop = new JPanel(new GridBagLayout());
        panelBot = new JPanel(new GridLayout(2, 4, 10, 10));
        panelRight = new JPanel(new GridBagLayout());
    }
    
    public void taoDang() {
        f.setLayout(new GridLayout(1, 2));
        cboStandard.setPreferredSize(new Dimension(txtName.getPreferredSize()));
        table.setFillsViewportHeight(true);
        scrollPaneTextArea.setPreferredSize(new Dimension(300, 100));
        txtName.setEditable(false);
        txtParentName.setEditable(false);
        txtContactNo.setEditable(false);
        textArea.setEditable(false);
        cboStandard.setEditable(false);
        cboStandard.setEnabled(false);
    }
    
    public void add() {
        JComponent mangPanelTop[] = {lblName, lblAddress, lblParentName, lblContactNo, lblStandard, txtName, scrollPaneTextArea, txtParentName, txtContactNo, cboStandard};
        addChung(panelTop, gbc, mangPanelTop, 5, 2, new Insets(5, 5, 20, 5), GridBagConstraints.WEST);
        
        JComponent mangPanelBot[] = {btnNew, btnNext, btnInsert, btnPrevious, btnEdit, btnDelete, btnUpdate, btnExit};
        addChung(panelBot, null, mangPanelBot, 2, 4, null, 0);
        
        JComponent mangPanelRight[] = {panelTop, panelBot};
        addChung(panelRight, gbc, mangPanelRight, 2, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
        JComponent mangF[] = {scrollPaneTable, panelRight};
        addChung(f, null, mangF, 1, 2, null, 0);
        
        cboStandard.setEditable(false);
        
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    public void event() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                lienKetSql();
                fillToTable();
            }
        });
        
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextComponent mangTxt[] = {txtName, textArea, txtParentName, txtContactNo};
                for (int i = 0; i < mangTxt.length; i++) {
                    mangTxt[i].setText("");
                }
                cboStandard.setSelectedIndex(0);
            }
        });
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int index = table.getSelectedRow();
                    Student student = list.get(index);
                    indexID = student.id;
                    txtName.setText(student.name);
                    textArea.setText(student.address);
                    txtParentName.setText(student.parentName);
                    txtContactNo.setText(student.phone);
                    cboStandard.setSelectedItem(student.standard);
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng trong bảng trước");
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = table.getSelectedRow();
                    Student student = list.get(index);
                    list.remove(index);
                    pst = con.prepareStatement("delete from student where id = ?");
                    pst.setString(1, student.id);
                    pst.executeUpdate();
                    fillToTable();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng trong bảng trước");
                }
            }
        });
        
        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiemTra();
                if (sai == 0 && trong == 0) {
                    try {
                        pst = con.prepareStatement("insert into student values(?, ?, ?, ?, ?)");
                        pst.setString(1, txtName.getText());
                        pst.setString(2, textArea.getText());
                        pst.setString(3, txtParentName.getText());
                        pst.setString(4, txtContactNo.getText());
                        pst.setString(5, (String) cboStandard.getSelectedItem());
                        pst.executeUpdate();
                        pst = con.prepareStatement("select * from student", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        rs = pst.executeQuery();
                        rs.last();
                        Student student = new Student();
                        student.id = rs.getString(1);
                        student.name = rs.getString(2);
                        student.address = rs.getString(3);
                        student.parentName = rs.getString(4);
                        student.phone = rs.getString(5);
                        student.standard = rs.getString(6);
                        list.add(student);
                        fillToTable();
                        setEdiable(false);
                    } 
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tienLui(1, "cuối");
            }
        });
        
        btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tienLui(0, "đầu");
            }
        });
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEdiable(true);
            }
        });
        
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kiemTra();
                if (sai == 0 && trong == 0) {
                    try {
                        int index = table.getSelectedRow();
                        Student student = list.get(index);

                        pst = con.prepareStatement("update student set name = ?, dia_chi = ?, parent_name = ?, phone = ?, nghe = ? where id = ?");
                        pst.setString(1, txtName.getText());
                        pst.setString(2, textArea.getText());
                        pst.setString(3, txtParentName.getText());
                        pst.setString(4, txtContactNo.getText());
                        pst.setString(5, (String) cboStandard.getSelectedItem());
                        pst.setString(6, student.id);
                        pst.executeUpdate();

                        student.name = txtName.getText();
                        student.address = textArea.getText();
                        student.parentName = txtParentName.getText();
                        student.phone = txtContactNo.getText();
                        student.standard = (String) cboStandard.getSelectedItem();
                        list.set(index, student);
                        setEdiable(false);
                        JOptionPane.showMessageDialog(f, "Update thành công");
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng trong bảng trước");
                    }
                    fillToTable();
                }
            }
        });
    }
}
