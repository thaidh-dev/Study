package bài.pkg26;

import java.util.Scanner;

public class Bài26 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        int a = 1;
        int so;
        int max = 0;
        int min = 0;
        System.out.printf("Nhập số thứ %d: ", a);
        a++;
        so = thai.nextInt();
        if (so < 0) {
        max = Math.min(max, so);
        }
        else if (so > 0) {
        min = Math.max(min, so);
        }
        do {
        System.out.printf("Nhập số thứ %d: ",a);
        a++;
        so = thai.nextInt();
        if (so != 0) {
        max = Math.max(max, so);
        min = Math.min(min, so);
        }
        }
        while (so != 0);
        System.out.println("Số lớn nhất: "+max);
        System.out.println("Số nhỏ nhất: "+min);
        }
        }
    