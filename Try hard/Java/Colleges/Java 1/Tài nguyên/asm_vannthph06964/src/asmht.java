
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moon
 */
public class asmht {
    static String[] hoten, hocluc, email;
    static double[] diem;
    static int n, dem;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    static void menu() {
        int so;
        do {
            System.out.println("+----------Menu----------+\n"
                    + "1. Nhập danh sách học viên\n"
                    + "2. Xuất danh sách học viên\n"
                    + "3. Tìm kiếm học viên theo khoảng điểm\n"
                    + "4. Tìm kiếm học viên theo học lực\n"
                    + "5. Tìm kiếm học viên theo mã số và cập nhật\n"
                    + "6. Sắp xếp học viên theo điểm\n"
                    + "7. Xuất 5 học viên có điểm cao nhất\n"
                    + "8. Tính điểm trung bình của lớp\n"
                    + "9. Xuất danh sách học viên có điểm trên điểm trung bình của lớp\n"
                    + "10. Tổng hợp số học viên theo học lực\n"
                    + "11. Exit\n"
                    + "+------------------------+");
            System.out.print("Chọn chức năng: ");
            so = sc.nextInt();
            switch (so) {
                case 1:
                    NhapDL();
                    break;
                case 2:
                    XuatDL();
                    break;
                case 3:
                    TimKiemHV_TheoDiem();
                    break;
                case 4:
                    TimKiemHV_TheoHL();
                    break;
                case 5:
                    TimKiemHV_TheoMS_CapNhat();
                    break;
                case 6:
                    SapXepHV_TheoDiem();
                    break;
                case 7:
                    XuatTop5HV_DiemCao();
                    break;
                case 8:
                    DiemTB();
                    break;
                case 9:
                    double avg = DiemTB();
                    XuatHV_TrenDiemTB(avg);
                    break;
                case 10:
                    TongHopHV_TheoHL();
                    break;
                case 11:
                    System.out.println("Exit.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Yêu cầu nhập từ 1-11");
            }
        } while (so != 11);
    }

    static void NhapDL() {
        System.out.println("Nhập danh sách học viên");
        System.out.print("Nhập số học viên: ");
        n = sc.nextInt();
        hoten = new String[n];
        diem = new double[n];
        email = new String[n];
        hocluc = new String[n];
        for (int i = 0; i < n; i++) {
            do {
                System.out.printf("\n--Sinh viên %d--", i + 1);
                hoten[i] = keyboard.readString("\n+Nhập họ và tên: ");
                diem[i] = keyboard.readDouble("+Nhập điểm: ");
                email[i] = keyboard.readString("+Nhập emai: ");
            } while (keyboard.check(hoten[i], diem[i], email[i], hoten, i) == false);

        }
    }

    static void XuatDL() {
        System.out.println("Danh sách học viên");
        for (int i = 0; i < n; i++) {
            System.out.printf("\n--Sinh viên %d--", i + 1);
            System.out.printf("\nHọ và tên: %s\n", hoten[i]);
            System.out.printf("Điểm: %.2f\n", diem[i]);
            System.out.printf("Email: %s\n", email[i]);
            hocluc[i] = Xarray.xeploai(diem[i]);
            System.out.printf("Học lực: %s\n", hocluc[i]);
        }
    }

    static void TimKiemHV_TheoDiem() {
        double min, max;
        dem = 0;
        System.out.println("Tìm kiếm học viên theo khoảng điểm");
        min = keyboard.readDouble("+Giới hạn điểm min: ");
        max = keyboard.readDouble("+Giới hạn điểm max: ");
        for (int i = 0; i < n; i++) {
            if (min <= diem[i] && diem[i] <= max) {
                System.out.printf("%s: %.2f\n", hoten[i], diem[i]);
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("*Không tìm thấy");
        }
    }

    static void TimKiemHV_TheoHL() {
        String timHL;
        dem = 0;
        System.out.println("Tìm kiếm học viên theo học lực");
        timHL = keyboard.readString("Nhập học lực cần tìm: ");
        for (int i = 0; i < n; i++) {
            if (hocluc[i].equalsIgnoreCase(timHL)) {
                System.out.printf("%s: %.2f\n", hoten[i], diem[i]);
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("*Không tìm thấy");
        }
    }

    static void TimKiemHV_TheoMS_CapNhat() {
        int maso;
        dem = 0;
        System.out.println("Tìm kiếm học viên theo mã số và cập nhật");
        System.out.printf("Nhập vị trí cần tìm(<=%d): ", n);
        maso = sc.nextInt();
        for (int i = 0; i < n; i++) {
            if (maso - 1 == i) {
                System.out.println(hoten[i]);
                System.out.println("Cập nhật thông tin");
                do {
                    hoten[i] = keyboard.readString("\n+Cập nhật họ và tên: ");
                    diem[i] = keyboard.readDouble("+Cập nhật điểm: ");
                    email[i] = keyboard.readString("+Cập nhật emai: ");
                } while (keyboard.check(hoten[i], diem[i], email[i], hoten, i) == false);
                System.out.println("Cập nhật thành công !");
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("*Không tìm thấy");
        }
    }

    static void SapXepHV_TheoDiem() {
        System.out.println("Sắp xếp học viên theo điểm");
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (diem[i] > diem[j]) {
                    Xarray.swap(hoten, i, j);
                    Xarray.swap(diem, i, j);
                    Xarray.swap(email, i, j);
                }
            }
        }
        System.out.println("Đã sắp xếp!");
    }

    static void XuatTop5HV_DiemCao() {
        System.out.println("5 học viên có điểm cao nhất");
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (diem[i] < diem[j]) {
                    Xarray.swap(hoten, i, j);
                    Xarray.swap(diem, i, j);
                    Xarray.swap(email, i, j);
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.printf("%s: %.2f\n", hoten[i], diem[i]);
            if (i == n - 1) {
                break;
            }
        }
    }

    static double DiemTB() {
        int i;
        double sum = 0;
        for (i = 0; i < n; i++) {
            sum += diem[i];
        }
        double avg = (double) sum / i;
        System.out.printf("Điểm trung bình của lớp: %.2f\n", avg);
        return avg;
    }

    static void XuatHV_TrenDiemTB(double avg) {
        dem = 0;
        System.out.println("Danh sách học viên có điểm trên điểm trung bình của lớp");
        for (int i = 0; i < n; i++) {
            if (diem[i] > avg) {
                System.out.printf("%s: %.2f\n", hoten[i], diem[i]);
                dem++;
            }
        }
        if (dem == 0) {
            System.out.println("*Không có sinh viên nào trên điểm trung bình của lớp");
        }

    }

    static void TongHopHV_TheoHL() {
        dem = 0;
        System.out.println("Tổng hợp số học viên theo học lực");
        String timHL = keyboard.readString("+Nhập học lực: ");
        for (int i = 0; i < n; i++) {
            if (hocluc[i].equalsIgnoreCase(timHL)) {
                dem++;
            }
        }
        System.out.printf("Học lực %s có: %d người\n", timHL, dem);
    }
    
    
}
