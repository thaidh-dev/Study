package Bai2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Chung {

    JFrame f;
    JTextArea textArea1, textArea2;
    JButton btnSend;
    JScrollPane scrollPane1, scrollPane2;
    GridBagConstraints gbc;
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    public Chung() {

    }

    public Chung(String a, int port) {
        taoDoiTuong(a, port);
        taoDang();
        event();
        add();
    }

    public void taoDoiTuong(String a, int port) {
        f = new JFrame(a);
        gbc = new GridBagConstraints();

        textArea1 = new JTextArea();
        scrollPane1 = new JScrollPane(textArea1);
        textArea2 = new JTextArea();
        scrollPane2 = new JScrollPane(textArea2);

        btnSend = new JButton("Send");

        try {
            socket = new Socket("localhost", port);
            // có thể chơi 1 trong 2 cách
//            socket = new Socket();
//            socket.connect(new InetSocketAddress("localhost", port));

            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void taoDang() {
        f.setLayout(new GridBagLayout());

        textArea1.setEditable(false);

        scrollPane1.setPreferredSize(new Dimension(400, 200));
        scrollPane2.setPreferredSize(new Dimension(300, 100));
    }

    public void add() {
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        f.add(scrollPane1, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        f.add(scrollPane2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        f.add(btnSend, gbc);

        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void event() {

        new Thread() {
            @Override
            public void run() {
                btnSend.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dos.writeUTF(textArea2.getText());
                            textArea1.append("Bạn: " + textArea2.getText() + "\n");
//                            textArea1.setText(textArea1.getText() + "Bạn: " + textArea2.getText() + "\n");
                            textArea2.setText("");
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                });

                textArea2.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            try {
                                dos.writeUTF(textArea2.getText().trim());
                                textArea1.append("Bạn: " + textArea2.getText().trim() + "\n");
                                textArea2.setText("");
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                });

            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        textArea1.append("Ai đó: " + dis.readUTF() + "\n");
//                        String a = dis.readUTF();
//                        textArea1.setText(textArea1.getText() + "Ai đó: " + a + "\n");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }.start();
    }
}
