package bài.pkg2;

import java.util.Scanner;

public class Bài2 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        int fo1 = 1;
        int fo2 = 2;
        System.out.print("Nhập 1 số: ");
        int m = thai.nextInt();
        int a = 0;
        if (m >= 3) {
        System.out.println(""+fo1);
        System.out.println(""+fo2);
        while (a < m) {
            a = fo1 + fo2;
            fo1 = fo2;
            fo2 = a;
            if (a < m) {
            System.out.println(""+a);
        }
        }
        }
        else {
            System.out.println("Bạn hãy nhập số lớn hơn hoặc bằng 3");
            }
    }
    }
