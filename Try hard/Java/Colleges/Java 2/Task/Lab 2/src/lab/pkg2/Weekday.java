package lab.pkg2;

import java.util.Scanner;

public class Weekday {
    public static final int sunday = 0;
    public static final int monday = 1;
    public static final int tuesday = 2;
    public static final int wednesday = 3;
    public static final int thursday = 4;
    public static final int friday = 5;
    public static final int saturday = 6;
}

class Checkday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số từ 0 đến 6: ");
        int a = sc.nextInt();
        if (a == Weekday.sunday || a == Weekday.saturday) {
            System.out.println("Ngày cuối tuần");
        }
        else if (a > 6 || a < 0) {
            System.out.println("Hãy nhập từ 0 đến 6");
        }
        else {
            System.out.println("Ngày trong tuần");
        }
    }
}
