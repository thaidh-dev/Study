package lab.pkg6;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class Bai1 {
    JFrame f;
    JPanel panelFilter, panelTop;
    JButton btnSearch, btnExit, btnDelete;
    JTable table;
    JLabel lblTitle;
    JTextField txtTitle;
    JScrollPane scrollPane;
    Object tenCot[] = {"ID", "Title", "Price"};
    GridBagConstraints gbc;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    List<Book> list = new ArrayList<>();
    DefaultTableModel model = new DefaultTableModel(tenCot, 0);
    
    public static void main(String[] args) {
        Bai1 a = new Bai1();
        a.taoDoiTuong();
        a.taoDang();
        a.add();
        a.event();
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Bài 1");
        gbc = new GridBagConstraints();
        
        panelFilter = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        lblTitle = new JLabel("Title");
        txtTitle = new JTextField(20);
        
        panelTop = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnSearch = new JButton("Search");
        btnExit = new JButton("Exit");
        
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        
        btnDelete = new JButton("Delete");
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        panelFilter.setBorder(new TitledBorder(new LineBorder(Color.darkGray, 1), "Filter"));
        scrollPane.setPreferredSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
    }
    
    public void add() {
        panelFilter.add(lblTitle);
        panelFilter.add(txtTitle);
        
        panelTop.add(panelFilter);
        panelTop.add(btnSearch);
        panelTop.add(btnExit);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        f.add(panelTop, gbc);
        gbc.gridy = 1;
        f.add(scrollPane, gbc);
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 10);
        f.add(btnDelete, gbc);
        
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
                fillToTable();
            }
        });
        
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (txtTitle.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(f, "Hãy nhập tên sách vào ô");
                    }
                    else {
                        pst = con.prepareStatement("select * from book where title = ?");
                        pst.setString(1, txtTitle.getText());
                        rs = pst.executeQuery();
                        String a[] = new String[3];
                        rs.next();
                        a[0] = rs.getString(1);
                        a[1] = rs.getString(2);
                        a[2] = rs.getString(3);
                        model.setRowCount(0);
                        model.addRow(a);
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int index = table.getSelectedRow();
                    Book book = list.get(index);
                    int confirm = JOptionPane.showConfirmDialog(f, "Do you want to delete?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        list.remove(index);
                        fillToTable();
                        pst = con.prepareStatement("delete from book where id = ?");
                        pst.setString(1, book.id);
                        pst.executeUpdate();
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng trong bảng");
                }
            }
        });
    }
    
    public void lienKetSql() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName = book", "thaidhph06986", "dht24111997");
            pst = con.prepareStatement("select * from book");
            rs =  pst.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.id = rs.getString(1);
                book.title = rs.getString(2);
                book.price = rs.getString(3);
                list.add(book);
            }
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void fillToTable() {
        model.setRowCount(0);
        for (Book book : list) {
            Object row[] = {book.id, book.title, book.price};
            model.addRow(row);
        }
    }
}
