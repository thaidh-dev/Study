package lab.pkg1;

abstract class SinhVienPoly {
    String hoTen, nganh;
    
    abstract double getDiem();
    
    SinhVienPoly(String hoTen, String nganh) {
        this.hoTen = hoTen;
        this.nganh = nganh;
    }
    
    String getHocLuc() {
        if (getDiem() < 5) {
            return "Yếu";
        }
        if (getDiem() < 6.5) {
            return "Trung bình";
        }
        if (getDiem() < 7.5) {
            return "Khá";
        }
        if (getDiem() < 9) {
            return "Giỏi";
        }
        if (getDiem() < 10) {
            return "Xuất sắc";
        }
        return "Nhập sai";
    }
    
    void xuat() {
        System.out.print("Họ tên: "+hoTen);
        System.out.print("\tNgành: "+nganh);
        System.out.printf("\nĐiểm: %.1f",getDiem());
        System.out.print("\tHọc lực: "+getHocLuc());
        System.out.println("");
    }
}

class SinhVienIT extends SinhVienPoly {
    double diemJava, diemCss, diemHtml;
    
    SinhVienIT(String hoTen, String nganh, double diemJava, double diemCss, double diemHtml) {
        super(hoTen, nganh);
        this.diemJava = diemJava;
        this.diemHtml = diemHtml;
        this.diemCss = diemCss;
    }
    
    @Override
    double getDiem() {
        return (2*diemJava + diemHtml + diemCss)/4;
    }
}

class SinhVienBiz extends SinhVienPoly {
    double diemMarketing, diemSales;
    
    SinhVienBiz(String hoTen, String nganh, double diemMarketing, double diemSales) {
        super(hoTen, nganh);
        this.diemMarketing = diemMarketing;
        this.diemSales = diemSales;
    }
    
    @Override
    double getDiem() {
        return (2*diemMarketing + diemSales)/3;
    }
}

class xuat {
    public static void main(String[] args) {
        SinhVienPoly a = new SinhVienBiz("Thai", "Biz", 7, 3);
        a.xuat();
        SinhVienPoly b = new SinhVienIT("Long", "UDPM", 3, 8, 2);
        b.xuat();
    }
} 
