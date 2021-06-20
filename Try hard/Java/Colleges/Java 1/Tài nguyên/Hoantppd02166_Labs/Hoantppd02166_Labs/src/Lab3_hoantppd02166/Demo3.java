package Lab3_hoantppd02166;

import java.util.Scanner;

public class Demo3 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập số tiền lương: ");
        int salary = Sc.nextInt();
        System.out.print("Nhập số tiền thưởng: ");
        int bonus = Sc.nextInt();
        int salaryTotal = salary + bonus;
        double thue;
        if (salaryTotal < 9000000) {
            System.out.println("Tiền thuế: " + salaryTotal * 0);
            System.out.println("Lương nhận được: " + salaryTotal);
        } else if (salaryTotal < 15000000) {
            thue = (salaryTotal - 9000000) * 0.1;
            System.out.printf("Tiền thuế: %.0f \n", thue);
            System.out.printf("Lương nhận được: %.0f \n", (salaryTotal - thue));
        } else if (salaryTotal < 30000000) {
            thue = (salaryTotal - 15000000) * 0.15 + (6000000 * 0.1);
            System.out.printf("Tiền thuế: %.0f \n", thue);
            System.out.printf("Lương nhận được: %.0f \n", (salaryTotal - thue));
        } else {
            thue = (salaryTotal - 30000000) * 0.2 + 15000000 * 0.15 + (6000000 * 0.1);
            System.out.printf("Tiền thuế: %.0f \n", thue);
            System.out.printf("Lương nhận được: %.0f \n ", (salaryTotal - thue));
        }
    }
}
