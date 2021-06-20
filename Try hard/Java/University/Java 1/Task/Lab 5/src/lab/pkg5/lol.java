package lab.pkg5;

import java.util.Scanner;

public class lol {
    Scanner sc = new Scanner(System.in);
    
    String nhapString(String message) {
        System.out.print(message);
        String input = sc.nextLine();
        return input;
    }
    
    double nhapDouble(String message) {
        System.out.print(message);
        double input = Double.parseDouble(sc.nextLine());
        return input;
    }
    
    void swap(double a[], int x, int y) {
        double temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    
    void swap(String a[], int x, int y) {
        String temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    
    String[] xoa(String a[], int m) {
        String b[] = new String[a.length - 1];
        System.arraycopy(a, 0, b, 0, m);
        System.arraycopy(a, m+1, b, m, a.length - m - 1);
        return b;
    }
    
    double[] xoa(double a[], int m) {
        double b[] = new double[a.length - 1];
        System.arraycopy(a, 0, b, 0, m);
        System.arraycopy(a, m+1, b, m, a.length - m - 1);
        return b;
    }
}
