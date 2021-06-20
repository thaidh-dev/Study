package lab.pkg4;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class Bai1 {
    JFrame f;
    JMenuBar menuBar;
    JMenu menuFile, menuColor, menuSystem;
    JToolBar toolBar;
    GridBagConstraints gbc;
    JLabel lblFile, lblHuman, lblClose;
    JEditorPane editorPane;
    JTextField txt;
    JPanel panel1, panel2;
    JScrollPane scrollPane;
    JMenuItem menuItemNew, menuItemOpen, menuItemSave, menuItemAboutUs, menuItemExit;
    JRadioButton radioButtonRed, radioButtonGreen, radioButtonBlue, radioButtonOtherColor;
    ButtonGroup buttonGroup;
    JFileChooser fileChooser;
    
    public static void main(String[] args) {
        Bai1 a = new Bai1();
        a.taoDoiTuong();
        a.taoDang();
        a.event();
        a.add();
    }
    
    public void taoDoiTuong() {
        f = new JFrame("Bài 1");
        gbc = new GridBagConstraints();
        txt = new JTextField("Dương Hồng Thái");
        fileChooser = new JFileChooser();
        
        menuBar = new JMenuBar();
        menuFile = new JMenu("Flie");
        menuColor = new JMenu("Color");
        menuSystem = new JMenu("System");

        lblFile = new JLabel(new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 4\\image\\lol.png"));
        lblHuman = new JLabel(new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 4\\image\\human.png"));
        lblClose = new JLabel(new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 4\\image\\close.png"));
        toolBar = new JToolBar(JToolBar.HORIZONTAL);

        panel1 = new JPanel(new GridBagLayout());
        panel2 = new JPanel(new GridBagLayout());

        editorPane = new JEditorPane();
        scrollPane = new JScrollPane(editorPane);

        menuItemAboutUs = new JMenuItem("About us");
        menuItemExit = new JMenuItem("Exit");
        menuItemNew = new JMenuItem("New", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 4\\image\\lol.png"));
        menuItemOpen = new JMenuItem("Open", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 4\\image\\open.jpg"));
        menuItemSave = new JMenuItem("Save", new ImageIcon("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 4\\image\\save.png"));
        
        radioButtonOtherColor = new JRadioButton("Other Color");
        radioButtonRed = new JRadioButton("Red");
        radioButtonGreen = new JRadioButton("Green");
        radioButtonBlue = new JRadioButton("Blue");
        buttonGroup = new ButtonGroup();
    }
    
    public void taoDang() {
        f.setLayout(new GridBagLayout());
        fileChooser.setCurrentDirectory(new File("C:\\Users\\Admin\\Desktop\\Try hard\\Java\\Colleges\\Java 3\\Task\\Java 3.Lab 4"));
        editorPane.setPreferredSize(new Dimension(1000, 500));
        
        txt.setEditable(false);
        txt.setHorizontalAlignment(0);
        txt.setPreferredSize(new Dimension(300, 50));
        
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        toolBar.setPreferredSize(new Dimension(300, 50));
        
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuColor.setMnemonic(KeyEvent.VK_C);
        menuSystem.setMnemonic(KeyEvent.VK_S);
        
        menuItemNew.setPreferredSize(new Dimension(150, 50));
        menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItemOpen.setPreferredSize(new Dimension(150, 50));
        menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItemSave.setPreferredSize(new Dimension(150, 50));
        menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        
        menuItemAboutUs.setPreferredSize(new Dimension(150, 30));
        menuItemAboutUs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        menuItemExit.setPreferredSize(new Dimension(150, 30));
        menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        panel1.setPreferredSize(new Dimension(400, 100));
        panel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
    }
    
    public void add() {
        menuSystem.add(menuItemAboutUs);
        menuSystem.add(menuItemExit);
        
        buttonGroup.add(radioButtonRed);
        buttonGroup.add(radioButtonGreen);
        buttonGroup.add(radioButtonBlue);
        buttonGroup.add(radioButtonOtherColor);
        
        JComponent mangMenuColor[] = {radioButtonRed, radioButtonGreen, radioButtonBlue};
        addChung(menuColor, null, mangMenuColor, 3, 1, null, 0);
        menuColor.addSeparator();
        menuColor.add(radioButtonOtherColor);
        
        JComponent mangMenuItem[] = {menuItemNew, menuItemOpen, menuItemSave};
        addChung(menuFile, null, mangMenuItem, 3, 1, null, 0);
        
        JComponent mangMenu[] = {menuFile, menuColor, menuSystem};
        addChung(menuBar, null, mangMenu, 1, 3, null, 0);

        JComponent mangLbl[] = {lblFile, lblHuman, lblClose};
        addChung(toolBar, null, mangLbl, 1, 3, null, 0);

        JComponent mangP1[] = {menuBar, toolBar};
        addChung(panel1, gbc, mangP1, 2, 1, new Insets(0, 5, 0, 5), GridBagConstraints.WEST);

        JComponent mangP2[] = {scrollPane, txt};
        addChung(panel2, gbc, mangP2, 2, 1, new Insets(20, 0, 0, 0), GridBagConstraints.CENTER);
        
        JComponent mangF[] = {panel1, panel2};
        addChung(f, gbc, mangF, 2, 1, new Insets(0, 5, 5, 5), GridBagConstraints.WEST);
        
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
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
        menuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editorPane.setText("");
            }
        });
        
        menuItemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int traVe = fileChooser.showOpenDialog(f);
                if (traVe == JFileChooser.APPROVE_OPTION) {
                    try {
                        editorPane.setPage("file:///" + fileChooser.getSelectedFile().getPath());
                    } 
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(f, "Lỗi mở file");
                    }
                }
            }
        });
        
        menuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int traVe = fileChooser.showSaveDialog(f);
                if (traVe == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter(""+fileChooser.getSelectedFile().getPath()));
                        bw.write(editorPane.getText());
                        bw.newLine();
                        bw.close();
                        JOptionPane.showMessageDialog(f, "Đã lưu");
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(f, "Không lưu được");
                    }
                }
            }
        });
        
        thayMau(radioButtonRed, Color.red);
        thayMau(radioButtonGreen, Color.GREEN);
        thayMau(radioButtonBlue, Color.BLUE);

        radioButtonOtherColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(f, "Chọn màu", Color.white);
                txt.setBackground(color);
                txt.setForeground(Color.white);
                txt.setFont(new Font("", Font.BOLD, 20));
            }
        });
        
        menuItemAboutUs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(f, "Tên: Dương Hồng Thái\nLớp: PT14201_UD");
            }
        });
        
        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        lblFile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                editorPane.setText("");
                lblFile.setBorder(null);
            }
        });
        
        
        lblHuman.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(f, "Tên: Dương Hồng Thái\nLớp: PT14201_UD");
                lblHuman.setBorder(null);
            }
        });
        
        lblClose.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
                lblClose.setBorder(null);
            }
        });
        
        thayBorder(lblFile);
        thayBorder(lblHuman);
        thayBorder(lblClose);

    }

    public void thayMau(JRadioButton jRadioButton, Color color) {
        jRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txt.setBackground(color);
                txt.setForeground(Color.white);
                txt.setFont(new Font("", Font.BOLD, 20));
            }
        });
    }
    
    public void thayBorder(JLabel lbl) {
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lbl.setBorder(new BevelBorder(BevelBorder.LOWERED));
            }
        });
    }
}

