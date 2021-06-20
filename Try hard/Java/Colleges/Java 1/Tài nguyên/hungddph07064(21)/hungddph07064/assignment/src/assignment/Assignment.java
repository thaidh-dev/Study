/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.util.Scanner;

/**
 *
 * @author Dang Hung
 */
public class Assignment {

    /**
     * @param args the command line arguments
     */
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int chon;
        do {
            showMenu();
            chon = chonChucNang();
            switch (chon) {
                case 1:
                    nhapDSHV();
                    break;
                case 2:
                    xuatDSHV();
                    break;
                case 3:
                    timHVtheoKhoangDiem();
                    break;
                case 4:
                    timHVtheoHocLuc();
                    break;
                case 5:
                    timVaCapNhatHVtheoMa();
                    break;
                case 6:
                    sapXepHVtheoDiem();
                    break;
                case 7:
                    xuatTop5HV();
                    break;
                case 8:
                    tinhDTBcuaLop();
                    break;
                case 9:
                    DSHVcoDTBtrenDTBcuaLop();
                    break;
                case 10:
                    tongSoHVtheoHocLuc();
                    break;
                case 0:
                    System.out.println("Ban da thoat chuong trinh");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ban da chon sai \nMoi nhap lai");
            }

        } while (chon != 0);
    }

    public static void showMenu() {

        System.out.println("|      Menu                                                   ");
        System.out.println("|      --------------------------------                      |");
        System.out.println("|   1. Nhap danh sach hoc vien                               |");
        System.out.println("|   2. Xuat danh sach hoc vien                               |");
        System.out.println("|   3. Tim hoc vien theo khoang diem                         |");
        System.out.println("|   4. Tim hoc vien theo hoc luc                             |");
        System.out.println("|   5. Tim va cap nhat thong tin hoc vien theo ma            |");
        System.out.println("|   6. Sap xep hoc vien theo diem                            |");
        System.out.println("|   7. Xuat 5 hoc vien co diem cao nhat                      |");
        System.out.println("|   8. Tinh diem trung binh(DTB) cua lop                     |");
        System.out.println("|   9. Danh sach hoc vien co DTB tren DTB cua lop            |");
        System.out.println("|   10. Tong so hoc vien theo hoc luc                        |");
        System.out.println("|   0. Thoat                                                 |");

    }

    public static int chonChucNang() {
        int chon = 0;
        System.out.print("Chon mot chuc nang: ");
        chon = sc.nextInt();
        sc.nextLine();
        return chon;
    }

    static HocVien[] hocVien = new HocVien[5];

    public static void nhapDSHV() {
        System.out.println("Nhap vao Danh sach hoc vien: ");
        
        for (int i = 0; i < hocVien.length; i++) {
            System.out.print("Nhap vao ten: ");
            String hoTen = sc.nextLine();
            while ("".equals(hoTen)) {
                System.out.println("Khong duoc de trong ho va ten");
                System.out.println("Nhap lai!");
                hoTen = sc.nextLine();
            }
            System.out.print("Nhap vao diem: ");
            float diem = Float.parseFloat(sc.nextLine());
            while (diem < 0 || diem > 10) {
                System.out.println("Diem trong khoang tu 0 den 10");
                System.out.println("Nhap lai!");
                diem = Float.parseFloat(sc.nextLine());
            }
            System.out.print("Nhap vao email: ");
            String email = sc.nextLine();
            String reEmail = "^(.+)@(.+)$";
            while (!email.matches(reEmail)) {
                System.out.println("Khong dung dang email");
                System.out.println("Nhap lai!");
                email = sc.nextLine();
            }
            hocVien[i] = new HocVien(hoTen, diem, email);
            System.out.println("");

        }
    }

    public static void xuatDSHV() {
        System.out.println("Danh sach hoc vien");
        for (HocVien hv : hocVien) {
            System.out.println(hv.getHoTen() + "\t" + hv.getDiem() + "\t" + hv.getEmail() + "\t" + hv.HocLuc());
        }
    }

    public static void timHVtheoKhoangDiem() {
        System.out.print("Nhap khoang diem: min = ");
        float min = Float.parseFloat(sc.nextLine());
        System.out.print("max = ");
        float max = Float.parseFloat(sc.nextLine());
        System.out.println("Danh sach hoc vien theo khoang diem tu " + min + "-" + max);
        for (HocVien hv : hocVien) {
            if (hv.getDiem() >= min && hv.getDiem() <= max) {
                System.out.println(hv.getHoTen() + "\t" + hv.getDiem() + "\t" + hv.getEmail());
            }
        }
    }

    public static void timHVtheoHocLuc() {
        System.out.print("Nhap hoc luc(Kem, Yeu, Trung binh, Kha, Gioi): ");
        String hocLuc = sc.nextLine();
        System.out.println("Danh sach hoc vien theo hoc luc " + hocLuc);
        for (HocVien hv : hocVien) {
            if (hocLuc.equalsIgnoreCase(hv.HocLuc())) {
                System.out.println(hv.getHoTen() + "\t" + hv.getDiem() + "\t" + hv.getEmail() + "\t" + hv.HocLuc());
            }
        }
    }

    public static void timVaCapNhatHVtheoMa() {
        System.out.print("Nhap ma hoc vien: ");
        int ma = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < hocVien.length; i++) {
            if (ma == i) {
                System.out.println(hocVien[i].getHoTen() + "\t" + hocVien[i].getDiem() + "\t" + hocVien[i].getEmail() + "\t" + hocVien[i].HocLuc());
                System.out.println("Ban co muon cap nhat thong tin hoc vien nay?(true, false)");
                boolean capNhat = Boolean.parseBoolean(sc.nextLine());
                if (capNhat) {
                    System.out.print("Ho va ten: ");
                    hocVien[i].setHoTen(sc.nextLine());
                    System.out.print("Diem: ");
                    hocVien[i].setDiem(Float.parseFloat(sc.nextLine()));
                    System.out.print("Email: ");
                    hocVien[i].setEmail(sc.nextLine());
                }
            }
        }
    }

    public static void sapXepHVtheoDiem() {
        for (int i = 0; i < hocVien.length - 1; i++) {
            for (int j = i + 1; j < hocVien.length; j++) {
                if (hocVien[i].getDiem() < hocVien[j].getDiem()) {
                    HocVien temp = hocVien[i];
                    hocVien[i] = hocVien[j];
                    hocVien[j] = temp;
                }
            }
        }
        System.out.println("Danh sach hoc vien sau khi sap xep");
        xuatDSHV();
    }

    public static void xuatTop5HV() {
        System.out.println("Danh sach top 5 hoc vien: ");
        for (int i = 0; i < 5; i++) {
            System.out.println(hocVien[i].getHoTen() + "\t" + hocVien[i].getDiem() + "\t" + hocVien[i].getEmail() + "\t" + hocVien[i].HocLuc());
        }
    }

    public static float tinhDTBcuaLop() {
        float sum = 0, avg;
        for (int i = 0; i < hocVien.length; i++) {
            sum += hocVien[i].getDiem();
        }
        avg = sum / hocVien.length;
        System.out.printf("Diem trung binh cua lop la: %.2f\n", avg);
        return avg;
    }

    public static void DSHVcoDTBtrenDTBcuaLop() {
        float avg = tinhDTBcuaLop();
        System.out.println("Danh sach hoc vien co DTB tren DTB cua lop");
        for (int i = 0; i < hocVien.length; i++) {
            if (hocVien[i].getDiem() > avg) {
                System.out.println(hocVien[i].getHoTen() + "\t" + hocVien[i].getDiem() + "\t" + hocVien[i].getEmail() + "\t" + hocVien[i].HocLuc());
            }
        }
    }

    public static void tongSoHVtheoHocLuc() {
        System.out.println("Tong so hoc vien theo hoc luc");
        String hocLuc[] = {"Kem", "Yeu", "Trung binh", "Kha", "Gioi", "Xuat sac"};
        for (int i = 0; i < hocLuc.length; i++) {
            int count = 0;
            for (HocVien hv : hocVien) {
                if (hocLuc[i].equalsIgnoreCase(hv.HocLuc())) {
                    count++;
                }
            }
            System.out.println("Hoc luc " + hocLuc[i] + ": " + count);
        }
    }

}
