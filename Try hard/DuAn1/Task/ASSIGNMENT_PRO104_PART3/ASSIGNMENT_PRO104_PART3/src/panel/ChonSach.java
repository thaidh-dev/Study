/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Thanh
 */
public class ChonSach {
    
    private JDialog sachDialog;
    private JPanel pnlSach, pnlTimSach, pnlCheckSach;
    private JScrollPane scrTacGia;
    private JLabel lblSachTim;
    private JButton btnXacNhan, btnHuy;
    private JTextField txtSachTim, txtMaSach;
    private JTable tblChonSach;
    private static final int BOOLEAN_COLUMN = 4;
//    
//    
//     sachDialog = new JDialog();
//        sachDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("fpt.png")));
//        sachDialog.setTitle("Chọn Tác Giả");
//        sachDialog.setLayout(new BorderLayout());
//        sachDialog.setMinimumSize(new Dimension(600, 463));
//        sachDialog.setResizable(false);
//        sachDialog.setLocationRelativeTo(null);
//        pnlSach = new JPanel(new BorderLayout());
//        lblSachTim = new JLabel("Tìm Kiếm");
//        txtSachTim.setFont(new Font("tahoma", 1, 17));
//        btnXacNhan = new JButton("Xác Nhận");
//        btnXacNhan.setPreferredSize(new Dimension(150, 30));
//        btnHuy = new JButton("Huỷ Chọn");
//        btnHuy.setPreferredSize(new Dimension(150, 30));
//        txtSachTim = new JTextField();
//        txtSachTim.setPreferredSize(new Dimension(400, 30));
//        tblChonSach = createTable();
//        tblChonSach.getModel().addTableModelListener(new ManHinhMT.CheckBoxModelListener());
//        scrTacGia = new JScrollPane();
//        scrTacGia.setViewportView(tblChonSach);
//        btnXacNhan.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (txtSachTim.getText().equals("")) {
//                    JOptionPane.showMessageDialog(null, "Mời Bạn Chọn Tác Giả ?", "Cảnh Báo", JOptionPane.WARNING_MESSAGE);
//                    txtSachTim.requestFocus();
//                } else {
//                    sachDialog.dispose();
////                    txtTacGia.setText(txtSachTim.getText());
//                }
//            }
//        });
//
////        txtTacGiaTim.addKeyListener(new KeyAdapter() {
////            @Override
////            public void keyPressed(KeyEvent e) {
////                String tacgia = txtTacGiaTim.getText().trim();
////                filterTG(tacgia);
////            }
////        });
//        btnHuy.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
////                huyToTableTG();
//                txtSachTim.setText("");
//            }
//        });
//        pnlTimSach = new JPanel(new FlowLayout(10, 38, 10));
//        pnlTimSach.add(lblSachTim);
//        pnlTimSach.add(txtSachTim);
//        
//        pnlCheckSach = new JPanel(new FlowLayout(10, 100, 10));
//        pnlCheckSach.add(btnXacNhan);
//        pnlCheckSach.add(btnHuy);
//        
//        pnlSach.add(pnlTimSach, BorderLayout.NORTH);
//        pnlSach.add(scrTacGia, BorderLayout.CENTER);
//        pnlSach.add(pnlCheckSach, BorderLayout.SOUTH);
//        sachDialog.getContentPane().add(pnlSach, BorderLayout.CENTER);
//        
//        sachDialog.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent windowEvent) {
//                sachDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//            }
//        });
        
//        txtSach.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
////                if (e.getButton() == MouseEvent.BUTTON3) {
////                    JPopupMenu popup = new ManHinhChinh.PopupmenuTblTG();
////                    popup.show(e.getComponent(), e.getX(), e.getY());
////                } else {
//                sachDialog.setVisible(true);
////                }
//            }
//        });

     private JTable createTable() {
        String[] cols = {"STT", "Tiêu Đề", "Ngôn Ngữ", "Chọn"};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, cols) {
            @Override
            public Class getColumnClass(int column) {
                if (column == 0 || column == 1 || column == 2 || column == 3) {
                    return String.class;
                }
                return column == BOOLEAN_COLUMN ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == BOOLEAN_COLUMN;
            }
        };
        JTable table = new JTable(model);
        table.setIntercellSpacing(new Dimension(10, 1));

        JTableHeader tableHeader = table.getTableHeader();
        ((DefaultTableCellRenderer) tableHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        return table;
    }

//// Checkbox Tác Giả
//    public class CheckBoxModelListener implements TableModelListener {
//
//        @Override
//        public void tableChanged(TableModelEvent e) {
//            int a = tblChonSach.getSelectedRow();
//            if (tblChonSach.isValid() == true) {
//                txtSachTim.setText("");
//                txtMaSach.setText("");
//                for (int i = 0; i < tblChonSach.getRowCount(); i++) {
//                    if (tblChonSach.getValueAt(i, 4) != null && String.valueOf(tblChonSach.getValueAt(i, 4)).equals("true")) {
//                        txtSachTim.setText(txtSachTim.getText() + String.valueOf(tblChonSach.getValueAt(i, 1) + ", "));
////                        txtMaTacGia.setText(txtMaTacGia.getText() + String.valueOf(tblChonSach.getValueAt(i, 0) + ", ").substring(4));
//                    }
//                }
//                if (txtSachTim.getText().length() != 0 && txtSachTim.getText().length() != 0) {
//                    i = 0; i < b.length; i++) {
////                b[i] = b[i].trim();
////                if (!b[i].equals("")) {
////                    int c = Integer.parseInt(b[i]);
////                    if (tg.getMaTG() == c) {
////                        row[3] = true;
////                        break;
////                    }
////                }
////            }
//            defaultTableModel.addRow(row);
//        }
//    } txtSachTim.setText(txtSachTim.getText().substring(0, txtSachTim.getText().length() - 2));
//                    txtMaSach.setText(txtMaSach.getText().substring(0, txtMaSach.getText().length() - 2));
//                }
//            }
//        }
//    }
//
//    void fillToTableSach() {
//        listSach = sachDAO.select();
//        defaultTableModel = (DefaultTableModel) tblChonSach.getModel();
//        defaultTableModel.setRowCount(0);
//        for (int i = 0; i < listSach.size(); i++) {
//            Object row[] = new Object[3];
//            row[0] = i + 1;
//            row[1] = listSach.get(i).getTieuDe();
//            row[2] = listSach.get(i).getNgonNgu();
////            for (int i = 0; i < b.length; i++) {
////                b[i] = b[i].trim();
////                if (!b[i].equals("")) {
////                    int c = Integer.parseInt(b[i]);
////                    if (tg.getMaTG() == c) {
////                        row[3] = true;
////                        break;
////                    }
////                }
////            }
//            defaultTableModel.addRow(row);
//        }
//    }
}
