package ass;

import java.util.Scanner;

public class keyboard {

    static Scanner sc = new Scanner(System.in);

    static String readString(String message) {
        System.out.print(message);
        String input = sc.nextLine();
        return input;
    }

    static double readdouble(String message) {
        System.out.print(message);
        double input = sc.nextDouble();
        sc.nextLine();
        return input;
    }

    static boolean kiemtra(String hoten, double diem, String email) {
        boolean a = true;
        String rehoten = "[a-zA-Z ]+";
        String reemail = "\\w+@\\w+(.\\w+){1,2}";
        if (!hoten.matches(rehoten)) {
            System.out.println("Mời bạn nhập lại tên");
            a = false;
        }

        if (!email.matches(reemail)) {
            System.out.println("Mời bạn nhập lại email");
            a = false;
        }

        if (diem < 0 || diem > 10) {
            System.out.println("Mời bạn nhập lại điểm");
            a = false;
        }
        return a;
    }

    static String xeploai(double diem) {
        String hocluc;
        if (diem < 5) {
            hocluc = "Yếu";
        } else if (diem < 6.5) {
            hocluc = "Trung bình";
        } else if (diem < 7.5) {
            hocluc = "Khá";
        } else if (diem < 9) {
            hocluc = "Giỏi";
        } else {
            hocluc = "Xuất sắc";
        }
        return hocluc;
    }

}
