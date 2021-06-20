package ass;
import java.util.Scanner;

public class Ass {
    static String [] hoten, hocluc, email;
    static double diem[],m,n, dtb;
    static int x, a, y;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        quanlyhocvien();
    }
    
    static void quanlyhocvien() {
        do {
        System.out.println("|      Menu                                                  |");
        System.out.println("|      --------------------------------                      |");
        System.out.println("|   1. Nhập danh sách học viên                               |");
        System.out.println("|   2. Xuất danh sách học viên                               |");
        System.out.println("|   3. Tìm học viên theo khoảng điểm                         |");
        System.out.println("|   4. Tìm học viên theo học lực                             |");
        System.out.println("|   5. Tìm và cập nhật thông tin học viên theo mã            |");
        System.out.println("|   6. Sắp xếp học viên theo điểm                            |");
        System.out.println("|   7. Xuất 5 học viên có điểm cao nhất                      |");
        System.out.println("|   8. Tính điểm trung bình(DTB) của lớp                     |");
        System.out.println("|   9. Danh sách học viên có DTB trên DTB của lớp            |");
        System.out.println("|   10. Tổng số học viên theo học lực                        |");
        System.out.println("|   0. Thoát                                                 |");
        System.out.println("|      --------------------------------                      |");
        System.out.print("    Mời bạn chọn 1 chức năng: ");
        a = sc.nextInt();
        
        switch (a) {
            case 1: yeucau1();
            break;
            case 2: yeucau2();
            break;
            case 3: yeucau3();
            break;
            case 4: yeucau4();
            break;
            case 5: yeucau5();
            break;
            case 6: yeucau6();
            break;
            case 7: yeucau7();
            break;
            case 8: yeucau8();
            break;
            case 9: yeucau9();
            break;
            case 10: yeucau10();
            break;
            case 0:
                System.out.println("Thoát");
                System.exit(0);
            default:
                System.out.println("Bạn hãy nhập chức năng từ 0 đến 10");
        }
        }
        while (a != 0);
    }

    static void yeucau1() {
        System.out.println("\nNHẬP DANH SÁCH HỌC VIÊN\n");
        System.out.print("Bạn muốn nhập bao nhiêu học viên: ");
        a = sc.nextInt();
        sc.nextLine();
        hoten = new String[a];
        email = new String[a];
        hocluc = new String[a];
        diem = new double[a];
        for (x = 0; x < a; x++) {
            do {
                System.out.printf("\nSinh viên %d\n",x+1);
                hoten[x] = keyboard.readString("Nhập họ tên: ");
                email[x] = keyboard.readString("Nhập email: ");
                diem[x] = keyboard.readdouble("Nhập điểm: ");
                hocluc[x] = keyboard.xeploai(diem[x]);
            }
            while (keyboard.kiemtra(hoten[x], diem[x], email[x]) == false);
        }
        System.out.println("");
    }
    
    static void yeucau2() {
        System.out.println("\nXUẤT DANH SÁCH HỌC VIÊN");
        for (x = 0; x < hoten.length; x++) {
            System.out.printf("\nSinh viên %d\n",x+1);
            hocluc[x] = keyboard.xeploai(diem[x]);
            System.out.println("Họ tên: "+hoten[x]);
            System.out.println("email: "+email[x]);
            System.out.println("Điểm: "+diem[x]);
            System.out.println("Học lực: "+hocluc[x]);
        }
        System.out.println("");
    }
    
    static void yeucau3() {
        System.out.println("\nTÌM HỌC VIÊN THEO KHOẢNG ĐIỂM\n");
        do {
        m = keyboard.readdouble("Nhập điểm min: ");
        n = keyboard.readdouble("Nhập điểm max: ");
        if (!(m < n || m >= 0 || m <= 10 || n >= 0 || n <= 10)) {
            System.out.println("Mời bạn nhập lại");
        }
        }
        while (!(m < n || m >= 0 || m <= 10 || n >= 0 || n <= 10));
        for (x = 0; x < diem.length; x++) {
            if (diem[x] >= m && diem[x] <= n) {
                System.out.printf("\nSinh viên %d\n",x+1);
                System.out.println("Họ tên: "+hoten[x]);
                System.out.println("email: "+email[x]);
                System.out.println("Điểm: "+diem[x]);
                System.out.println("Học lực: "+hocluc[x]);
            }
        }
        System.out.println("");
    }
    
    static void yeucau4() {
        System.out.println("\nTÌM HỌC VIÊN THEO HỌC LỰC\n");
        String q = keyboard.readString("Nhập học lực: ");
        for (x = 0; x < hocluc.length; x++) {
            if (hocluc[x].equalsIgnoreCase(q)) {
                System.out.printf("\nSinh viên %d\n",x+1);
                System.out.println("Họ tên: "+hoten[x]);
                System.out.println("email: "+email[x]);
                System.out.println("Điểm: "+diem[x]);
                System.out.println("Học lực: "+hocluc[x]);
            }
        }
        System.out.println("");
    }
    
    static void yeucau5() {
        System.out.println("\nTÌM VÀ CẬP NHẬT THÔNG TIN HỌC VIÊN THEO MÃ\n");
        double ma;
        do {
            ma = keyboard.readdouble("Chọn mà sinh viên muốn cập nhật: ");
            if (ma <= 0 || ma > hoten.length) {
            System.out.println("Không có thông tin hoặc mã số không hợp lệ, mời bạn nhập lại");
        }
        }
        while (ma <= 0 || ma > hoten.length);
        for (x = 0; x < hoten.length; x++) {
            if (x == ma - 1) {
                do {
                    hoten[x] = keyboard.readString("Cập nhật tên: ");
                    email[x] = keyboard.readString("Cập nhật email: ");
                    diem[x] = keyboard.readdouble("Cập nhật điểm: ");
                }
                while (keyboard.kiemtra(hoten[x], diem[x], email[x]) == false);
                System.out.println("Cập nhật thành công");
            }
        }
        System.out.println("");
    }
    
    static void yeucau6() {
        System.out.println("\nSẮP XẾP HỌC VIÊN THEO ĐIỂM\n");
        System.out.print("Chọn 1 để sắp xếp tăng, chọn 0 để sắp xếp giảm: ");
        int chon = sc.nextInt();
        switch (chon) {
            case 1:
                xarrays.sapxeptang(diem, email);
                xarrays.sapxeptang(diem, hoten);
                break;
            case 0:
                xarrays.sapxepgiam(diem, hoten);
                xarrays.sapxepgiam(diem, email);
                break;
        }
        System.out.println("");
    }
    
    static void yeucau7() {
        System.out.println("\nXUẤT 5 HỌC VIÊN CÓ ĐIỂM CAO NHẤT");
        
        xarrays.sapxepgiam(diem, hoten);
        xarrays.sapxepgiam(diem, email);
        
        for (x = 0; x < 5; x++) {
            if (x >= diem.length) {
                System.out.printf("\nChỉ có %d học viên\n", x);
                break;
            }
            System.out.printf("\nSinh viên %d\n",x+1);
            hocluc[x] = keyboard.xeploai(diem[x]);
            System.out.println("Họ tên: "+hoten[x]);
            System.out.println("email: "+email[x]);
            System.out.println("Điểm: "+diem[x]);
            System.out.println("Học lực: "+hocluc[x]);
        }
        System.out.println("");
    }
    
    static void yeucau8() {
        System.out.println("\nTÍNH ĐIỂM TRUNG BÌNH (DTB) CỦA LỚP\n");
        double tong = 0;
        for (x = 0; x < diem.length; x++) {
            tong += diem[x];
        }
        dtb = tong/(x);
        System.out.println("Điểm trung bình cả lớp: "+dtb);
        System.out.println("");
    }
    static void yeucau9() {
        System.out.println("\nDANH SÁCH HỌC VIÊN CÓ DTB TRÊN DTB CỦA LỚP");
        for (x = 0; x < hoten.length; x++) {
            if (diem[x] > dtb) {
                System.out.printf("\nSinh viên %d\n",x+1);
                hocluc[x] = keyboard.xeploai(diem[x]);
                System.out.println("Họ tên: "+hoten[x]);
                System.out.println("email: "+email[x]);
                System.out.println("Điểm: "+diem[x]);
                System.out.println("Học lực: "+hocluc[x]);
            }
    }
        System.out.println("");
    }
    
    static void yeucau10() {        
        System.out.println("\nTỔNG SỐ HỌC VIÊN THEO HỌC LỰC\n");
        String hl[] = {"Yếu", "Trung bình", "Khá", "Giỏi", "Xuất sắc"};
        for (x = 0; x < hl.length; x++) {
            int dem = 0;
            for (y = 0; y < hocluc.length; y++) {
                if (hl[x].equalsIgnoreCase(hocluc[y])) {
                    dem++;
                }
            }
            System.out.printf("%s: %d\n",hl[x], dem);
        }
        System.out.println("");
}
}
    
