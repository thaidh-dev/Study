package lab.pkg5;

import java.util.Scanner;

public class Lmao {
    static Scanner sc = new Scanner(System.in);
    
    static String nhapString(String message) {
        System.out.print(message);
        String input = sc.nextLine();
        return input;
    }
    
    static double nhapDouble(String message) {
        System.out.print(message);
        double input = Double.parseDouble(sc.nextLine());
        return input;
    }
    
    static void swap(String a[], int x, int y) {
        String temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    
    static void swap(double a[], int x, int y) {
        double temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    
    static String[] xoa(String a[], int x) {
        String b[] = new String[a.length - 1];
        System.arraycopy(a, 0, b, 0, x);
        System.arraycopy(a, x+1, b, x, a.length - x - 1);
        return b;
    }
    
    static double[] xoa(double a[], int x) {
        double b[] = new double[a.length - 1];
        System.arraycopy(a, 0, b, 0, x);
        System.arraycopy(a, x+1, b, x, a.length - x - 1);
        return b;
    }
}
