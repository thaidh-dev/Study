package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bai3 {
    JFrame f;
    JButton btnMessage, btnInput, btnConfirm, btnOption;
    String ten;
    
    public static void main(String[] args) {
        new Bai3();
    }
    
    Bai3() {
        form();
        event();
    }
    
    public void form() {
        f = new JFrame("Bài 3");
        f.setLayout(new GridLayout(2, 2, 10, 10));
        
        btnMessage = new JButton("Message Dialog");
        btnInput = new JButton("Input Dialog");
        btnConfirm = new JButton("Confirm Dialog");
        btnOption = new JButton("Option Dialog");
        
        f.add(btnMessage);
        f.add(btnInput);
        f.add(btnConfirm);
        f.add(btnOption);
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void event() {
        btnInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ten = JOptionPane.showInputDialog(f, "Tên của bạn là: ", "Input", 3);
            }
        });
        
        btnMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ten == null) {
                    JOptionPane.showMessageDialog(f, "Hãy nhập tên của bạn vào ô Input Dialog trước", "Error", 0);
                }
                else {
                    JOptionPane.showMessageDialog(f, "Tên của bạn là: "+ten, "Message", 1);
                }
            }
        });
        
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ten == null) {
                    JOptionPane.showMessageDialog(f, "Hãy nhập tên của bạn vào ô Input Dialog trước", "Error", 0);
                }
                else {
                    int a = JOptionPane.showConfirmDialog(f, "Bạn có chắc tên của bạn là " + ten + " không?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION, 2);
                    if (a == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(f, "Chào "+ten);
                    }
                    else {
                        ten = JOptionPane.showInputDialog("Tên của bạn là: ");
                    }
                }
            }
        });
        
        btnOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a[] = {"Java", "C++", "VB", "PHP", "Perl"};
                int ngonNgu = JOptionPane.showOptionDialog(f, "Ngôn ngữ lập trình yêu thích của bạn là", "Option", 0, 3, null, a, null);
                switch (ngonNgu) {
                    case 0: JOptionPane.showMessageDialog(f, a[ngonNgu]+" là 1 ngôn ngữ hay");
                    break;
                    case 1: JOptionPane.showMessageDialog(f, a[ngonNgu]+" là 1 ngôn ngữ hay");
                    break;
                    case 2: JOptionPane.showMessageDialog(f, a[ngonNgu]+" là 1 ngôn ngữ hay");
                    break;
                    case 3: JOptionPane.showMessageDialog(f, a[ngonNgu]+" là 1 ngôn ngữ hay");
                    break;
                    case 4: JOptionPane.showMessageDialog(f, a[ngonNgu]+" là 1 ngôn ngữ hay");
                    break;
                }
            }
        });
    }
    
}
