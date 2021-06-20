package bài.pkg8;

import java.util.Scanner;

public class Bài8 {

    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        System.out.print("Số a: ");
        int a = thai.nextInt();
        System.out.print("Số b: ");
        int b = thai.nextInt();
        System.out.print("Số c: ");
        int c = thai.nextInt();
        if (a>b) {
            if (a>c) {
                System.out.println("Số lớn nhất: "+a);
            }
            else {
                System.out.println("Số lớn nhất: "+c);
            }
        }
        else {
            if (b>c) {
                System.out.println("Số lớn nhất: "+b);
        }
            else {
                System.out.println("Số lớn nhất: "+c);
            }
        }
        
        if (a<b) {
            if (a<c) {
                System.out.println("Số nhỏ nhất: "+a);
            }
            else {
                System.out.println("Số nhỏ nhất: "+c);
            }
        }
        else {
            if (b<c) {
                System.out.println("Số nhỏ nhất: "+b);
            }
            else {
                System.out.println("Số nhỏ nhất: "+c);
            }
        }
        }
}