import Model.NhanVien;
import Helper.*;
import DAO.NhanVienDAO;
import java.util.List;

public class NewClass {
    public static void main(String[] args) {
        //NhanVien nv = new NhanVien("aaaaajhkyijbc", "Cái lồn j cơ", "abc76876884");
        NhanVienDAO dao = new NhanVienDAO();
        dao.fillToTable();

        //thêm
        //dao.insert(nv);
        
        //sửa
        //dao.update(nv);
        
        //xóa
        dao.delete();
        
        List<NhanVien> list = dao.fillToTable();
        
        for (NhanVien x : list) {
            System.out.println(x.getTaiKhoan() + ", " + x.getTen() + ", " + x.getMatKhau());
        }
        
        
    }
}
