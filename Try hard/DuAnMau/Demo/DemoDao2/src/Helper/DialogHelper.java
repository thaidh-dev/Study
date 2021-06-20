package Helper;

import javax.swing.*;

public class DialogHelper {
    public static void message(JComponent parentComponent, Object message, String title, int messageType) {
        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }
}
