package ass;

import static ass.Employee.*;
import java.io.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Method {
    
    public void moi() {
        txtMa.setText("");
        txtTen.setText("");
        txtTuoi.setText("");
        txtEmail.setText("");
        txtLuong.setText("");
    }
    
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        model.setRowCount(0);
        
        for (Employee e : list) {
            Object row[] = {e.ma, e.ten, e.tuoi, e.email, e.luong};
            model.addRow(row);
        }
    }
    
    public void showDetail() {
        try {
            int index = tbl.getSelectedRow();
            Employee e = list.get(index);
            txtMa.setText(e.ma);
            txtTen.setText(e.ten);
            txtTuoi.setText(String.valueOf(e.tuoi));
            txtEmail.setText(e.email);
            txtLuong.setText(String.valueOf(e.luong));
            lblTrangThai.setText("Record: "+(index+1)+" of "+list.size());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(f, "Mời bạn chọn 1 dòng cụ thể trong bảng");
        }
    }
    
    public void delete() {
        int index = tbl.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng cụ thể trong bảng trước");
        }
        else {
            list.remove(index);
        }
    }
    
    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Ass.txt"));
            oos.writeObject(list);
            oos.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(f, "Lỗi ghi file");
        }
    }
    
    public void open() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ass.txt"));
            Object object = ois.readObject();
            list = (List<Employee>) object;
            ois.close();

            Employee e = list.get(0);
            txtMa.setText(e.ma);
            txtTen.setText(e.ten);
            txtTuoi.setText(String.valueOf(e.tuoi));
            txtEmail.setText(e.email);
            txtLuong.setText(String.valueOf(e.luong));
            lblTrangThai.setText("Record: 1 of "+list.size());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(f, "Không có dữ liệu để mở");
            moi();
        }
    }
    
    public void them() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Ass.txt"));
            Object object = ois.readObject();
            list = (List<Employee>) object;
        } 
        catch (Exception ex) {
        }
    
        Employee e = new Employee();
        int sai = 0;
        int trong = 0;
        
        // mã
        e.ma = txtMa.getText();
        if (e.ma.isEmpty()) {
            trong++;
        }
        else {
            if (!e.ma.matches(e.regexMa)) {
                sai++;
                JOptionPane.showMessageDialog(f, "Mã chỉ chứa các kí tự là chữ và số");
            }

            for (int i = 0; i < list.size(); i++) {
                Employee employee = list.get(i);
                if (employee.ma.equals(e.ma)) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Trùng mã");
                }
            }
        }
        
        // tên
        e.ten = txtTen.getText();
        if (e.ten.isEmpty()) {
            trong++;
        }
        else {
            if (!e.ten.matches(e.regexTen)) {
                sai++;
                JOptionPane.showMessageDialog(f, "Tên không đúng định dạng");
            }
        }
        
        // tuổi
        if (txtTuoi.getText().isEmpty()) {
            trong++;
        }
        else {
            try {
                e.tuoi = Double.parseDouble(txtTuoi.getText());
            }
            catch (Exception ex) {
                sai++;
                JOptionPane.showMessageDialog(f, "Tuổi không đúng định dạng");
            }
            if (e.tuoi <= 16 || e.tuoi >= 55) {
                sai++;
                JOptionPane.showMessageDialog(f, "Tuổi chỉ nằm trong khoảng 16 đến 55");
            }
        }
        
        // email
        e.email = txtEmail.getText();
        if (e.email.isEmpty()) {
            trong++;
        }
        else {
            if (!e.email.matches(e.regexEmail)) {
                sai++;
                JOptionPane.showMessageDialog(f, "Email không đúng định dạng");
            }
        }
        
        // lương
        if (txtLuong.getText().isEmpty()) {
            trong++;
        }
        else {
            try {
                e.luong = Double.parseDouble(txtLuong.getText());
            }
            catch (Exception ex) {
                sai++;
                JOptionPane.showMessageDialog(f, "Lương không đúng định dạng");
            }
            if (e.luong < 5000000) {
                sai++;
                JOptionPane.showMessageDialog(f, "Lương phải lớn hơn 5 triệu đồng");
            }
        }
        
        if (trong >= 1) {
            JOptionPane.showMessageDialog(f, "Không để trông ô");
        }
        
        if (sai == 0 && trong == 0) {
            list.add(e);
            eventMoi = 0;
            JOptionPane.showMessageDialog(f, "Lưu file thành công");
        }
    }
    
    public void update() {
        int index = tbl.getSelectedRow();
        Employee e = new Employee();
        int sai = 0;
        int trong = 0;
        
        if (index == -1) {
            JOptionPane.showMessageDialog(f, "Nếu muốn lưu thêm hãy bấm New trước còn nếu muốn cập nhật thông tin hãy chọn 1 dòng muốn cập nhật trên bảng trước");
        }
        else {
            // mã
            e.ma = txtMa.getText();
            Employee employee = new Employee();
            if (e.ma.isEmpty()) {
                trong++;
            }
            else {
                if (!e.ma.matches(e.regexMa)) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Mã chỉ chứa các kí tự là chữ và số");
                }
                else {

                }
                try {
                    employee = list.get(index);
                    if (!employee.ma.equals(e.ma)) {
                        for (int i = 0; i < list.size(); i++) {
                            employee = list.get(i);
                            if (employee.ma.equals(e.ma) && sai == 0) {
                                sai++;
                                JOptionPane.showMessageDialog(f, "Trùng mã");
                            }
                        }
                    }
                }
                catch (Exception ex) {
                }
            }
            
            // tên
            e.ten = txtTen.getText();
            if (e.ten.isEmpty()) {
                trong++;
            }
            else {
                if (!e.ten.matches(e.regexTen)) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Tên không đúng định dạng");
                }
            }
            
            // tuổi
            if (txtTuoi.getText().isEmpty()) {
                trong++;
            }
            else {
                try {
                    e.tuoi = Double.parseDouble(txtTuoi.getText());
                }
                catch (Exception ex) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Tuổi không đúng định dạng");
                }
                if (e.tuoi <= 16 || e.tuoi >= 55) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Tuổi chỉ nằm trong khoảng 16 đến 55");
                }
            }

            // email
            e.email = txtEmail.getText();
            if (e.email.isEmpty()) {
                trong++;
            }
            else {
                if (!e.email.matches(e.regexEmail)) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Email không đúng định dạng");
                }
            }
            
            // lương
            if (txtLuong.getText().isEmpty()) {
                trong++;
            }
            else {
                try {
                    e.luong = Double.parseDouble(txtLuong.getText());
                }
                catch (Exception ex) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Lương không đúng định dạng");
                }
                if (e.luong < 5000000) {
                    sai++;
                    JOptionPane.showMessageDialog(f, "Lương phải lớn hơn 5 triệu đồng");
                }
            }

            if (trong >= 1) {
                JOptionPane.showMessageDialog(f, "Không để trông ô");
            }

            if (sai == 0 && trong == 0) {
                list.set(index, e);
                JOptionPane.showMessageDialog(f, "Cập nhật thành công");
            }
        }
    }
    
    public void tienLui(int tienLui) {
        int index = tbl.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(f, "Hãy chọn 1 dòng cụ thể trong bảng trước");
        }
        else {
            Employee e = new Employee();
            e.ma = txtMa.getText();
            e.ten = txtTen.getText();
            e.tuoi = Double.parseDouble(txtTuoi.getText());
            e.email = txtEmail.getText();
            e.luong = Double.parseDouble(txtLuong.getText());
            for (int i = 0; i < list.size(); i++) {
                if (e.equals(list.get(i))) {
                    index = i;
                }
            }
            if (tienLui == 1) {
                try {
                    e = list.get(index+1);
                    txtMa.setText(e.ma);
                    txtTen.setText(e.ten);
                    txtTuoi.setText(String.valueOf(e.tuoi));
                    txtEmail.setText(e.email);
                    txtLuong.setText(String.valueOf(e.luong));
                    lblTrangThai.setText("Record: "+(index+2)+" of "+list.size());
                }
                catch (Exception ex) {
                }
            }
            else {
                try {
                    e = list.get(index-1);
                    txtMa.setText(e.ma);
                    txtTen.setText(e.ten);
                    txtTuoi.setText(String.valueOf(e.tuoi));
                    txtEmail.setText(e.email);
                    txtLuong.setText(String.valueOf(e.luong));
                    lblTrangThai.setText("Record: "+index+" of "+list.size());
                }
                catch (Exception ex) {
                }
            }
        }
    }
    
    public void dauCuoi(int dauCuoi) {
        try {
            if (dauCuoi == 1) {
                Employee e = list.get(list.size()-1);
                txtMa.setText(e.ma);
                txtTen.setText(e.ten);
                txtTuoi.setText(String.valueOf(e.tuoi));
                txtEmail.setText(e.email);
                txtLuong.setText(String.valueOf(e.luong));
                lblTrangThai.setText("Record: "+list.size()+" of "+list.size());
            }
            else {
                Employee e = list.get(0);
                txtMa.setText(e.ma);
                txtTen.setText(e.ten);
                txtTuoi.setText(String.valueOf(e.tuoi));
                txtEmail.setText(e.email);
                txtLuong.setText(String.valueOf(e.luong));
                lblTrangThai.setText("Record: 1 of "+list.size());
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(f, "Không có dữ liệu");
        }
    }
    
    public void tim() {
        int ok = 0;
        for (int i = 0; i < list.size(); i++) {
            if (txtMa.getText().equals(list.get(i).ma)) {
                txtMa.setText(list.get(i).ma);
                txtTen.setText(list.get(i).ten);
                txtTuoi.setText(String.valueOf(list.get(i).tuoi));
                txtEmail.setText(list.get(i).email);
                txtLuong.setText(String.valueOf(list.get(i).luong));
                lblTrangThai.setText("Record: "+(i+1)+" of "+list.size());
                ok++;
            }
        }
        if (ok == 0) {
            JOptionPane.showMessageDialog(f, "Không tìm thấy");
        }
    }
}
