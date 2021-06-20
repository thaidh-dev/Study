package demojlegu;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class Lol {
    JFrame win;
    JPanel pnlKhu, pnlNut;
    JButton btnThem, btnXoa, btnCapNhat, btnXoaEvent;
    GridBagConstraints gbc;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int tenBan = 1;
    int x, y;
    Robot robot;

    public Lol() {
        lienKetSQL();
        taoDoiTuong();
        taoDang();
        event();
        add();
    }
    
    public void taoDoiTuong() {
        win = new JFrame();
        pnlKhu = new JPanel();
        pnlNut = new JPanel(new GridBagLayout());
        gbc = new  GridBagConstraints();
        
        try {
            robot = new Robot();
        } 
        catch (Exception ex) {
        }
        
        btnThem = new JButton("Thêm bàn");
        btnXoa = new JButton("Xóa bàn");
        btnCapNhat = new JButton("Cập nhật vị trí bàn");
        btnXoaEvent = new JButton(" Xóa event");
    }
    
    public void taoDang() {
        win.setLayout(new GridBagLayout());
        pnlKhu.setLayout(null);
        pnlKhu.setPreferredSize(new Dimension(1000, 600));
        pnlKhu.setBorder(new EtchedBorder(EtchedBorder.RAISED));
    }
    
    public void add() {
        JComponent a[] = {btnThem, btnXoa, btnCapNhat, btnXoaEvent};
        addChung(pnlNut, gbc, a, 4, 1, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER);
        
        JComponent b[] = {pnlKhu, pnlNut};
        addChung(win, gbc, b, 1, 2, new Insets(5, 5, 5, 5), 17);
        
        win.pack();
        win.setLocationRelativeTo(null);
        win.setDefaultCloseOperation(3);
        win.setVisible(true);
    } 
    
    public void event() {
        win.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    tenBan = 1;
                    pnlKhu.removeAll();

                    pst = con.prepareStatement("select x, y from ban_an");
                    rs = pst.executeQuery();
                    
                    while (rs.next()) {
                        JPanel x = banGhe();
                        x.setLocation(rs.getInt(1), rs.getInt(2));
                        pnlKhu.add(x);
                    }
                    
                    pnlKhu.revalidate();
                    pnlKhu.repaint();

                } 
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlKhu.add(banGhe());
                pnlKhu.revalidate();
                pnlKhu.repaint();
                
                try {
                    con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=empdb", "thaidh", "dht24111997");
                    pst = con.prepareStatement("insert into ban_an(ten_khu, x, y) values (?, ?, ?)");
                    pst.setString(1, "A");
                    pst.setInt(2, 0);
                    pst.setInt(3, 0);
                    pst.executeUpdate();
                } 
                catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        
        btnXoaEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component c[] = pnlKhu.getComponents();
        
                for (int i = 0; i < c.length; i++) {
                    MouseListener ml[] = c[i].getMouseListeners();
                    for (int j = 0; j < ml.length; j++) {
                        c[i].removeMouseListener(ml[j]);
                    }

                    MouseMotionListener mml[] = c[i].getMouseMotionListeners();
                    for (int j = 0; j < mml.length; j++) {
                        c[i].removeMouseMotionListener(mml[j]);
                    }
                }
            }
        });
        
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnlKhu.remove(pnlKhu.getComponentCount()-1);
                pnlKhu.revalidate();
                pnlKhu.repaint();
            }
        });
        
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component ban[] = pnlKhu.getComponents();
                boolean overlap = false;

                abc:for (int i = 0; i < ban.length; i++) {
                    for (int j = 0; j < ban.length; j++) {
                        if (i != j) {
                            if (ban[i].getX() - ban[j].getX() > 100 || ban[i].getY() - ban[j].getY() > 100) {

                            }
                            else if (ban[i].getX() - ban[j].getX() < -100 || ban[i].getY() - ban[j].getY() < -100) {

                            }
                            else {
                                overlap = true;
                                ban[i].setBackground(Color.red);
                                System.out.println("Chồng nhau");
                                break abc;
                            }
                        }
                    }
                }
                
                if (overlap == false) {
                    for (int i = 0; i < ban.length; i++) {
                        try {
                            pst = con.prepareStatement("update ban_an set x = ?, y = ? where ma = ?");
                            pst.setInt(1, ban[i].getX());
                            pst.setInt(2, ban[i].getY());
                            pst.setInt(3, i+1);
                            pst.executeUpdate();
                        } 
                        catch (SQLException ex) {
                            System.out.println(ex);
                        }
                    }                    
                }
            }
        });
    }
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lol();
            }
        });
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

    public void lienKetSQL() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=empdb", "thaidh", "dht24111997");
            System.out.println("Kết nối SQl thành công");
        } 
        catch (Exception e) {
        }
    }
    
    public JButton ban() {
        JButton btnBan = new JButton("a"+tenBan);
        tenBan++;
        
        return btnBan;
    }
    
    public JPanel ghe(int width, int height, LayoutManager layout) {
        JPanel pnl = new JPanel(layout);
        JPanel pnlGhe = new JPanel();
        pnlGhe.setPreferredSize(new Dimension(width, height));
        pnlGhe.setBackground(Color.BLACK);
        pnl.add(pnlGhe);
        return pnl;
    }
    
    public JPanel banGhe() {
        JPanel pnlBanGhe = new JPanel((new BorderLayout()));
        //pnlBanGhe.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        pnlBanGhe.setSize(new Dimension(100, 100));
        
        pnlBanGhe.add(ghe(50, 10, new FlowLayout(FlowLayout.CENTER, 0, 0)), BorderLayout.NORTH);
        pnlBanGhe.add(ghe(50, 10, new FlowLayout(FlowLayout.CENTER, 0, 0)), BorderLayout.SOUTH);
        pnlBanGhe.add(ghe(10, 50, new GridBagLayout()), BorderLayout.WEST);
        pnlBanGhe.add(ghe(10, 50, new GridBagLayout()), BorderLayout.EAST);
        pnlBanGhe.add(ban(), BorderLayout.CENTER);
        
        pnlBanGhe.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }
        });
        
        int i = pnlKhu.getWidth() - pnlBanGhe.getWidth(); // tọa độ x tối đa của bàn
        int j = pnlKhu.getHeight() - pnlBanGhe.getHeight(); // tọa độ y tối đa của bàn

        pnlBanGhe.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int a = e.getX() + e.getComponent().getX() - x; // tọa độ x của bàn vs khu
                int b = e.getY() + e.getComponent().getY() - y; // tọa độ y của bàn vs khu

                e.getComponent().setLocation(a, b);

                double u = e.getComponent().getX(); // tọa độ x của bàn vs khu
                double v = e.getComponent().getY(); // tọa độ y của bàn vs khu

                if (u <= 0) {
                    if (v <= 0) {
                        e.getComponent().setLocation(0, 0);
                    }
                    else if (v >= j) {
                        e.getComponent().setLocation(0, j);
                    }
                    else {
                        e.getComponent().setLocation(0, b);
                    }
                    locationMouse(e);
                }
                else if (u >= i /*tọa độ x tối đa mà chuột di được trên khu*/) {
                    if (v <= 0) {
                        e.getComponent().setLocation(i, 0);
                    }
                    else if (v >= j) {
                        e.getComponent().setLocation(i, j);
                    }
                    else {
                        e.getComponent().setLocation(i, b);
                    }
                    locationMouse(e);
                }
                else {
                    if (v <= 0) {
                        e.getComponent().setLocation(a, 0);
                        locationMouse(e);
                    }
                    else if (v >= j /*tọa độ y tối đa mà chuột di được trên khu*/) {
                        e.getComponent().setLocation(a, j);
                        locationMouse(e);
                    }
                }
            }
        });
        
        return pnlBanGhe;
    }
    
    public void locationMouse(MouseEvent e) {
        Point p = e.getComponent().getLocationOnScreen();
        try {
            robot.mouseMove((int) p.getX() + x, (int) p.getY() + y);
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
