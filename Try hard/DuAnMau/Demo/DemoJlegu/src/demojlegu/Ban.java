package demojlegu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;

public class Ban extends JButton {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
            static int x = 1;
    
    public Ban() {
        try {
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=empdb", "thaidh", "dht24111997");
            pst = con.prepareStatement("insert into ban_an(ten_khu, x, y) values (?, ?, ?)");
            pst.setString(1, "A");
            pst.setInt(2, 0);
            pst.setInt(3, 0);
            pst.executeUpdate();
            setText("A"+x);
            x++;
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(getParent().getX());
            }
        });
    }
}
