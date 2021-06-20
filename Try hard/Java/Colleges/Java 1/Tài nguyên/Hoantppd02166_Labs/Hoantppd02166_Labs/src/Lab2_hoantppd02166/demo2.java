package Lab2;

import java.util.Scanner;

public class demo2 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập tuổi của nhóc đi: ");
        int age = Sc.nextInt();
        String result = age >= 18 ? "Cưới vợ đi" : "Cày ruộng tiếp đi";
        System.out.println("Lời khuyên của anh Hoàn: " + result);
    }
}
