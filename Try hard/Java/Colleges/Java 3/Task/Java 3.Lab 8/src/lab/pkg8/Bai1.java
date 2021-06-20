package lab.pkg8;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import javax.swing.*;
import javax.swing.border.*;

public class Bai1 {
    JFrame f;
    JLabel lblTieuDe, lblName, lblPass, lblTo, lblSubject, lblMessage, lblFile;
    JTextField txtName, txtTo, txtSubject;
    JPasswordField passwordField;
    JTextArea textAreaMessage, textAreaFile;
    JScrollPane scrollPaneMessage, scrollPaneFile;
    JButton btnSend, btnChonFile;
    JPanel panelSend, panelReceive, panelButtonGroup;
    GridBagConstraints gbc;
    JFileChooser fileChooser;
    Properties p;
    Session s;
    Message m;
    JRadioButton radioButtonCC, radioButtonBCC;
    ButtonGroup buttonGroup;
    BodyPart bp;
    Multipart mp;
    
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
        fileChooser = new JFileChooser(new File(System.getProperty("user.dir") + "\\mail"));
        
        p = new Properties();
                
        lblTieuDe = new JLabel("<html><p style=\"font-size: 20px; color: blue\">Send Mail</p></html>");
        
        panelSend = new JPanel(new GridBagLayout());
        lblName = new JLabel("Username");
        lblPass = new JLabel("Password");
        txtName = new JTextField(20);
        passwordField = new JPasswordField(20);
        
        panelReceive = new JPanel(new GridBagLayout());
        lblTo = new JLabel("To");
        lblSubject = new JLabel("Subject");
        lblMessage = new JLabel("Message");
        txtTo = new JTextField(20);
        txtSubject = new JTextField(20);
        textAreaMessage = new JTextArea();
        scrollPaneMessage = new JScrollPane(textAreaMessage);
        btnSend = new JButton("Send");
        btnChonFile = new JButton("Chọn file");
        lblFile = new JLabel("File");
        textAreaFile = new JTextArea();
        scrollPaneFile = new JScrollPane(textAreaFile);

        panelButtonGroup = new JPanel(new GridLayout(2, 1, 10, 10));
        radioButtonBCC = new JRadioButton("BCC");
        radioButtonCC = new JRadioButton("CC");
        buttonGroup = new ButtonGroup();
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        
        panelSend.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Send", TitledBorder.LEFT, TitledBorder.TOP, new Font("tahoma", Font.BOLD, 15)));
        panelReceive.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED), "Receive", TitledBorder.LEFT, TitledBorder.TOP, new Font("tahoma", Font.BOLD, 15)));
        
        scrollPaneMessage.setPreferredSize(new Dimension(200, 100));
        scrollPaneFile.setPreferredSize(new Dimension(200, 100));

        p.put("mail.smtp.auth", true);
        p.put("mail.smtp.starttls.enable", true);
        p.put("mail.smtp.host", "smtp.gmail.com"); // host mặc định của smtp protocol
        p.put("mail.smtp.port", 587); // cổng mặc định
    }
    
    public void add() {
        JComponent d[] = {lblName, lblPass, txtName, passwordField};
        addChung(panelSend, gbc, d, 2, 2, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        panelButtonGroup.add(radioButtonCC);
        panelButtonGroup.add(radioButtonBCC);
        buttonGroup.add(radioButtonCC);
        buttonGroup.add(radioButtonBCC);
        
        JComponent b[] = {lblTo, lblSubject, lblMessage, lblFile, panelButtonGroup, txtTo, txtSubject, scrollPaneMessage, scrollPaneFile, btnSend};
        addChung(panelReceive, gbc, b, 5, 2, new Insets(5, 5, 5, 5), GridBagConstraints.WEST);
        
        gbc.gridy = 4;
        panelReceive.add(btnChonFile, gbc);
        gbc.insets = new Insets(5, 80, 5, 5);
        panelReceive.add(btnChonFile, gbc);
        
        JComponent c[] = {lblTieuDe, panelSend, panelReceive};
        addChung(f, gbc, c, 3, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
    
    public void event() {
        btnChonFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int traVe = fileChooser.showOpenDialog(f);
                if (traVe == JFileChooser.APPROVE_OPTION) {
                    try {
                        textAreaFile.append(fileChooser.getSelectedFile().getPath() + "\n");
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (radioButtonCC.isSelected()) {
                        guiMail(Message.RecipientType.CC);
                    }

                    if (radioButtonBCC.isSelected()) {
                        guiMail(Message.RecipientType.BCC);
                    }

                    if (!radioButtonCC.isSelected() && !radioButtonBCC.isSelected()) {
                        guiMail(Message.RecipientType.TO);
                    }
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
    
    public void guiMail(RecipientType r) {
        try {
            Authenticator a = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(txtName.getText(), new String(passwordField.getPassword()));
                }
            };

            s = Session.getInstance(p, a);

            m = new MimeMessage(s);

            m.setRecipients(r, InternetAddress.parse(txtTo.getText()));
            m.setSubject(txtSubject.getText());

            mp = new MimeMultipart();

            bp = new MimeBodyPart();
            bp.setContent(textAreaMessage.getText(), "text/html; charset=utf-8");

            mp.addBodyPart(bp);
            
            String chuoi = textAreaFile.getText();
            while (chuoi.contains("\n")) {
                bp = new MimeBodyPart();
                File f = new File(chuoi.substring(0, chuoi.indexOf("\n")));
                bp.setDataHandler(new DataHandler(new FileDataSource(f)));
                
                mp.addBodyPart(bp);
                
                chuoi = chuoi.substring(chuoi.indexOf("\n") + 1, chuoi.length());
            }

            m.setContent(mp);
            Transport.send(m);
            JOptionPane.showMessageDialog(f, "Gửi thành công");
            
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void addChung(Container c, GridBagConstraints gbc, JComponent mang[], int row, int col, Insets insets, int anchor) {
        int z = 0;
        if (gbc == null) {
            for (int x = 0; x < col; x++) {
                for (int y = 0; y < row; y++) {
                    c.add(mang[z]);
                    z++;
                }
            }
        }
        else {
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
}
