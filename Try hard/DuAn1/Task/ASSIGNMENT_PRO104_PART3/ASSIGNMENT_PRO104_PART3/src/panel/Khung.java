package panel;

import java.awt.*;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public interface Khung {

    public abstract void taoDoiTuong();

    public abstract void taoDang();

    public abstract void event();

    public abstract void add();
    Dimension d = new Dimension(86, 25);

    public static void addChung(Container c, GridBagConstraints gbc, JComponent mang[], int row, int col, Insets insets, int anchor) {
        int z = 0;
        if (gbc == null) {
            for (int x = 0; x < col; x++) {
                for (int y = 0; y < row; y++) {
                    c.add(mang[z]);
                    z++;
                }
            }
        } else {
            for (int x = 0; x < col; x++) {
                for (int y = 0; y < row; y++) {
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

    public static JPanel taoJPanel(LayoutManager layout, Border border) {
        JPanel pnl = new JPanel(layout);
        pnl.setBorder(border);
        return pnl;
    }

    public static JTable taoTable(Class types[], String... tenCot) {
        String name[] = new String[tenCot.length];
        for (int i = 0; i < tenCot.length; i++) {
            name[i] = tenCot[i];
        }

        DefaultTableModel model = new DefaultTableModel(name, 0);

        JTable tbl;

        if (types != null) {
            tbl = new JTable(model) {

                @Override
                public Class getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

        } else {
            tbl = new JTable(model) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

        }
        
        JTableHeader tblHeader = tbl.getTableHeader();
        ((DefaultTableCellRenderer) tblHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        
        tbl.setIntercellSpacing(new Dimension(10, 1));
        tbl.setFillsViewportHeight(true);
        tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbl.setRowHeight(30);

        return tbl;
    }

    public static JScrollPane taoJScrollPane(JTable tbl, JTextArea txt, Dimension d) {
        JScrollPane scr = new JScrollPane();
        if (tbl != null) {
            scr.setViewportView(tbl);
        }

        if (txt != null) {
            scr.setViewportView(txt);
        }

        scr.setPreferredSize(d);
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scr;
    }

    public static JButton taoButton(String name) {
        JButton btn = new JButton(name);
        btn.setPreferredSize(d);
        btn.setFocusable(false);
        return btn;
    }

    void fillToTable();

    void showItem(int a);

    void search();

    void insert();

    void update();

    void delete();

    void clear();

    void editOn();

    void editOff();

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static boolean isEmptyByte(final byte[] data) {
        return IntStream.range(0, data.length).parallel().allMatch(i -> data[i] == 0);
    }
}
