package lab.pkg3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;

public class Bai4 {
    JFrame f;
    JPanel p1, p2, p3, p4;
    JLabel lblTieuDe, lblFirstName, lblLastName, lblContact, lblAddress, lblHighestQualification, lblSpecification, lblEnrollFor, lblHobbies, lblSport;
    JTextField txtFirstName, txtLastName, txtContact, txtHighestQualification, txtSpecification, txtEnrollFor, txtHobbies, txtSport;
    JTextArea txaAddress;
    JScrollPane spAddress;
    JButton btnSave, btnReset, btnExit;
    GridBagConstraints gbc;
    
    public static void main(String[] args) {
        Bai4 a = new Bai4();
        a.taoDoiTuong();
        a.taodang();
        a.add();
        a.event();
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Bài 4");
        gbc = new GridBagConstraints();
        
        p1 = new JPanel(new GridBagLayout());
        p2 = new JPanel(new GridBagLayout());
        p3 = new JPanel(new GridBagLayout());
        p4 = new JPanel(new FlowLayout(1, 10, 20));
        
        lblTieuDe = new JLabel();
        lblFirstName = new JLabel("First Name");
        lblLastName = new JLabel("Last Name");
        lblContact = new JLabel("Constact");
        lblAddress = new JLabel("Address");
        lblHighestQualification = new JLabel("Highest Qualification");
        lblSpecification = new JLabel("Specification");
        lblEnrollFor = new JLabel("Enroll For");
        lblHobbies = new JLabel("Hobbies");
        lblSport = new JLabel("Sport");
        
        btnSave = new JButton("Save");
        btnReset = new JButton("Reset");
        btnExit = new JButton("Exit");
        
        txaAddress = new JTextArea(7, 30);
        spAddress = new JScrollPane(txaAddress);
        
        txtFirstName = new JTextField(30);
        txtLastName = new JTextField(30);
        txtContact = new JTextField(30);
        txtHighestQualification = new JTextField(30);
        txtSpecification = new JTextField(30);
        txtEnrollFor = new JTextField(30);
        txtHobbies = new JTextField(30);
        txtSport = new JTextField(30);
    }
    
    public void taodang() {
        f.setLayout(new GridBagLayout());
        
        lblTieuDe.setText("<html><p style=\"color: purple; font-size:30px\">STUDENT REGISTRATION</p></html>");
        
        spAddress.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        p1.setPreferredSize(new Dimension(600, 250));
        p1.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "Personal Details"));
        p2.setPreferredSize(new Dimension(600, 150));
        p2.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "Academic Details"));
        p3.setPreferredSize(new Dimension(600, 100));
        p3.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "Extracurricular Details"));
        p4.setPreferredSize(new Dimension(600, 70));
        p4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
    }
    
    public void add() {
        JComponent mangP1[] = {lblFirstName, txtFirstName, lblLastName, txtLastName, lblContact, txtContact, lblAddress, spAddress};
        addChung(p1, gbc, mangP1, 4, 2, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST);
        
        JComponent mangP2[] = {lblHighestQualification, txtHighestQualification, lblSpecification, txtSpecification, lblEnrollFor, txtEnrollFor};
        addChung(p2, gbc, mangP2, 3, 2, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST);

        JComponent mangP3[] = {lblHobbies, txtHobbies, lblSport, txtSport};
        addChung(p3, gbc, mangP3, 2, 2, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST);
        
        JComponent button[] = {btnSave, btnReset, btnExit};
        addChung(p4, null, button, 1, 3, null, 0);
        
        JComponent cF[] = {lblTieuDe, p1, p2, p3, p4};
        addChung(f, gbc, cF, 5, 1, new Insets(10, 20 ,0 ,20), GridBagConstraints.CENTER);
        
        f.pack();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
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
    
    public void event() {
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextComponent txtAndTxa[] = {txtFirstName, txtLastName, txtContact, txtHighestQualification, txtSpecification, txtEnrollFor, txtHobbies, txtSport, txaAddress};
                int trong = 0;

                for (int i = 0; i < txtAndTxa.length; i++) {
                    if (txtAndTxa[i].getText() == null) {
                        trong++;
                    }
                }
                
                if (trong > 0) {
                    JOptionPane.showMessageDialog(f, "Không để trống");
                }
                else {
                    JOptionPane.showMessageDialog(f, "First Name: "+txtFirstName.getText() + 
                            "\nLast Name: "+txtLastName.getText() +
                            "\nContact: "+txtContact.getText() +
                            "\nAddress: "+txaAddress.getText() +
                            "\nHighest Qualification: "+txtHighestQualification.getText() +
                            "\nSpecification: "+txtSpecification.getText() +
                            "\nEnroll For: "+txtEnrollFor.getText() +
                            "\nHobbies: "+txtHobbies.getText() +
                            "\nSport: "+txtSport.getText()
                            );
                }
            }
        });
        
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextComponent txtAndTxa[] = {txtFirstName, txtLastName, txtContact, txtHighestQualification, txtSpecification, txtEnrollFor, txtHobbies, txtSport, txaAddress};
                for (int i = 0; i < txtAndTxa.length; i++) {
                    txtAndTxa[i].setText("");
                }
            }
        });
    }
}
