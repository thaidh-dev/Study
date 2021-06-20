package lab.pkg3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Bai3 {
    JFrame f;
    JSlider slider;
    JLabel lblGiaTriHienTai, lblFontSize;
    JTextField txt;
    JButton btn;
    JPanel p;
    GridBagConstraints gbc;
    
    public static void main(String[] args) {
        Bai3 a = new Bai3();
        a.taoDoiTuong();
        a.taoDang();
        a.event();
        a.add();
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Bài 3");
        slider = new JSlider(0, 50, 0);
        lblGiaTriHienTai = new JLabel("<html><p style=\"Color: blue; Font-size: 20px\">Giá trị hiện tại</p></html>");
        lblFontSize = new JLabel("Font Size");
        txt = new JTextField(10);
        btn = new JButton("Set Value");
        p = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        gbc = new GridBagConstraints();
    }
    
    public void taoDang() {
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        
        lblFontSize.setForeground(Color.blue);

        f.setLayout(new GridLayout(2, 1));
    }
    
    public void add() {
        JComponent mangP[] = {lblGiaTriHienTai, txt, btn, lblFontSize};
        addChung(p, null, mangP, 1, 4, null, 0);
        
        JComponent mangF[] = {slider, p};
        addChung(f, null, mangF, 2, 1, null, 0);
        
        f.setSize(700, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);    
    }
    
    public void event() {
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                txt.setText(String.valueOf(value));
                lblFontSize.setFont(new Font("tahoma", Font.BOLD, value));
            }
        });
        
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                int value = slider.getValue();
                txt.setText(""+value);
                lblFontSize.setFont(new Font("tahoma", Font.BOLD, value));
            }
        });
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(txt.getText());
                    if (value <= 50 && value >= 0) {
                        slider.setValue(value);
                        lblFontSize.setFont(new Font("tahoma", Font.BOLD, value));
                    }
                    else {
                        JOptionPane.showMessageDialog(f, "Nhập số từ 0 đến 50");
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(f, "Nhập số thôi");
                }
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

/* windowActivated: đang để cửa sổ jframe java, bật add jav xem, bật lại cửa sổ java thì nó hoạt động
 đưa xuống thanh taskbar rồi đưa lên nó cũng hoạt động
 nói chung là nó hoạt động khi mình đặt nó từ cửa sổ không hoạt động thành hoạt động
 viết code làm sao cho cửa sổ hiện phát thực hiện windowListener luôn, chứ cửa sổ hiện lên rồi làm việc khác thì windowListener không nhận
 tức là f.setVisible(true) luôn phải là dòng chạy cuối cùng trong việc tạo form */

/* Tương ứng với mỗi đối tượng Timer là một luồng 
Implementation note: All constructors start a timer thread.
*/