package Lab2;

import java.util.Scanner;

public class Demo3tamgiac {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.println("Nhập cạnh a: ");
        Double a = Sc.nextDouble();
        System.out.println("Nhập cạnh b: ");
        Double b = Sc.nextDouble();
        System.out.println("Nhập cạnh c: ");
        Double c = Sc.nextDouble();
        boolean result = a + b > c || a + c > b || b + c > a;
        System.out.println("Đây có phải là 3 cạnh của một tam giác không? " + result);

    }

}
