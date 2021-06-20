package Lab8_hoantppd02166;

public class Mainbai1 {

    public static void main(String[] args) {
        Bai1_Sanpham sp1 = new Bai1_Sanpham();
        Bai1_Sanpham sp2 = new Bai1_Sanpham();
        sp1.nhap();
        sp1.xuat();
        sp2.nhap();
        sp2.xuat();
        Bai1_Sanpham sp3 = new Bai1_Sanpham();
        sp3.xuat();
        Bai1_Sanpham sp4 = new Bai1_Sanpham("SumSung",5000000,100000);
        sp4.xuat();
    }
}
