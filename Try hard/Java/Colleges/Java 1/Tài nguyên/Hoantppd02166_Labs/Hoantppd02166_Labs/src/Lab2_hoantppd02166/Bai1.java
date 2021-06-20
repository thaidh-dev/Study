package Lab2;

import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        Scanner Sc = new Scanner(System.in);
        System.out.print("Nhập tên sản phẩm: ");
        String Productname = Sc.nextLine();
        System.out.print("Nhập đơn giá: ");
        int unitprice = Sc.nextInt();
        System.out.print("Nhập số lượng: ");
        int amount = Sc.nextInt();
        Double tax = unitprice * amount * 0.1;
        System.out.println("Tên sản phẩm: " + Productname);
        System.out.println("Đơn giá: " + unitprice);
        System.out.println("Số lượng: " + amount);
        System.out.println("Thuế nhập khẩu: " + tax);
        System.out.println("Tổng tiền phải trả: " + (tax + unitprice * amount));
    }

}
