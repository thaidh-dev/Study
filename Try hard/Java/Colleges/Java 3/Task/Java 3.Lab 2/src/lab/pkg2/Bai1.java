package lab.pkg2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Bai1 {
    JFrame f;
    JLabel lblName, lblAddress, lblSex, lblQualification, lblHobby;
    JButton btnValidate, btnReset;
    JComboBox cboQualification;
    JRadioButton radSex1, radSex2, radHobby1, radHobby2, radHobby3;
    JTextField txtName;
    JTextArea textArea;
    JPanel pSex, pHobby, pFooter;
    ButtonGroup bgHobby, bgSex;
    
    public static void main(String[] args) {
        new Bai1();
    }
    
    Bai1() {
        form();
        event();
    }
    
    public void form() {
        f = new JFrame("Bài 1");
        f.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        Font font = new Font("", Font.PLAIN, 20);
        
        lblName = new JLabel("Name:");
        lblName.setFont(font);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        f.add(lblName, gbc);
        
        lblAddress = new JLabel("Address:");
        lblAddress.setFont(font);
        gbc.gridy = 1;
        f.add(lblAddress, gbc);
        
        lblSex = new JLabel("Sex:");
        lblSex.setFont(font);
        gbc.gridy = 2;
        f.add(lblSex, gbc);
        
        txtName = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        f.add(txtName, gbc);
        
        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        gbc.gridy = 1;
        f.add(textArea, gbc);

        bgSex = new ButtonGroup();
        radSex1 = new JRadioButton("Male");
        radSex2 = new JRadioButton("Femate");
        bgSex.add(radSex1);
        bgSex.add(radSex2);
        pSex = new JPanel(new GridLayout(2,1));
        pSex.add(radSex1);
        pSex.add(radSex2);
        pSex.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        gbc.gridy = 2;
        f.add(pSex, gbc);
        
        lblQualification = new JLabel("Qualification:");
        lblQualification.setFont(font);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 30, 10, 10);
        f.add(lblQualification, gbc);
        
        lblHobby = new JLabel("Hobby:");
        lblHobby.setFont(font);
        gbc.gridy = 1;
        f.add(lblHobby, gbc);
        
        String a[] = {"Graduate", "Student"};
        cboQualification = new JComboBox(a);
        cboQualification.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        f.add(cboQualification, gbc);
        
        radHobby1 = new JRadioButton("Reading");
        radHobby2 = new JRadioButton("Singing");
        radHobby3 = new JRadioButton("Dancing");
        bgHobby = new ButtonGroup();
        bgHobby.add(radHobby1);
        bgHobby.add(radHobby2);
        bgHobby.add(radHobby3);
        pHobby = new JPanel(new GridLayout(3,1));
        pHobby.add(radHobby1);
        pHobby.add(radHobby2);
        pHobby.add(radHobby3);
        pHobby.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        gbc.gridy = 1;
        f.add(pHobby, gbc);

        btnValidate = new JButton("Validate");
        btnReset = new JButton("Reset");
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.CENTER);
        fl.setHgap(20);
        pFooter = new JPanel(fl);
        pFooter.add(btnValidate);
        pFooter.add(btnReset);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        f.add(pFooter, gbc);
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    public void event() {
        String regexName = "[a-zA-Z ]*";
        String regexAddress = "[a-zA-z0-9, ]*";
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                textArea.setText("");
                cboQualification.setSelectedIndex(0);
                bgSex.clearSelection();
                bgHobby.clearSelection();
            }
        });
        
        btnValidate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int trong = 0;
                int sai = 0;
                
                if (txtName.getText().isEmpty()) {
                    trong++;
                }
                else {
                    if (!txtName.getText().matches(regexName)) {
                        sai++;
                        JOptionPane.showMessageDialog(f, "Tên không đúng định dạng");
                    }
                }
                
                if (textArea.getText().isEmpty()) {
                    trong++;
                }
                else {
                    if (!textArea.getText().matches(regexAddress)) {
                        sai++;
                        JOptionPane.showMessageDialog(f, "Địa chỉ không đúng định dạng");
                    }
                }
                
                if (!radSex1.isSelected() && !radSex2.isSelected()) {
                    trong++;
                }
                
                if (!radHobby1.isSelected() && !radHobby2.isSelected() && !radHobby3.isSelected()) {
                    trong++;
                }
                
                if (trong > 0) {
                    JOptionPane.showMessageDialog(f, "Không để trống ô");
                }
                
                if (trong == 0 && sai == 0) {
                    JOptionPane.showMessageDialog(f, "Tên: "+txtName.getText()
                                                        +"\nĐịa chỉ: "+textArea.getText()
                                                        +"\nGiới tính: "+(radSex1.isSelected() ? "Nam" : "Nữ")
                                                        +"\nTrình độ chuyên môn: "+cboQualification.getSelectedItem()
                                                        +"\nSở thích: "+(radHobby1.isSelected() ? "Đọc sách" : radHobby2.isSelected() ? "Hát" : "Nhảy") 
                    );
                }
            }
        });
    }
    
}
