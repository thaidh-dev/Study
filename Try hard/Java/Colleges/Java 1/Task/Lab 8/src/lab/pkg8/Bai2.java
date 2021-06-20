package lab.pkg8;

public class Bai2 {
    
    public static void main(String[] args) {
        Bai1 a = new Bai1();
        Bai1 b = new Bai1();
        a. tensp = a.nhapstring("Nhập tên sản phẩm: ");
        a.dongia = a.nhapdouble("Nhập giá: ");
        a.giamgia = a.nhapdouble("Nhập giảm giá: ");
        b.tensp = b.nhapstring("\nNhập tên sản phẩm: ");
        b.dongia = b.nhapdouble("Nhập giá: ");
        b.giamgia = b.nhapdouble("Nhập giảm giá: ");
        a.xuat();
        b.xuat();
    }
}
