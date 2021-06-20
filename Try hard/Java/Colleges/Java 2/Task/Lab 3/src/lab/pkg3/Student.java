package lab.pkg3;

public class Student {
    String ten, nganh;
    double diem;
    
    public String hocLuc() {
        if (diem < 3) {
            return "Kém";
        }
        if (diem < 5) {
            return "Yếu";
        }
        if (diem< 6.5) {
            return "Trung bình";
        }
        if (diem< 7.5) {
            return "Khá";
        }
        if (diem < 9) {
            return "Giỏi";
        }
        return "Xuất sắc";
    }
    
    public boolean thuong() {
        return diem>= 7.5;
    }
}
