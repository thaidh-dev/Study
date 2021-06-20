package lab.pkg5;

import java.util.Scanner;

public class Baithem {
    public static void main(String[] args) {
        Scanner thai = new Scanner(System.in);
        double a[] = new double[7];
        double lol;
        int x = 0;
        for (lol = 1; lol <= a.length; lol++) {
        System.out.printf("Nhập số thứ %.0f: ", lol);
        a[x] = thai.nextDouble();
        x++;
    }
        System.out.print("a[] = ");
        for (double y : a) {
            System.out.print(""+y);
            System.out.print(" ");
        }
        
        double b[] = new double[4];
        System.out.print("\nDãy các số ở vị trí lẻ là a[] = ");
        for (x = 0; x < a.length; x += 2) {
            System.arraycopy(a, x, b, x/2, 1);
            System.out.print(""+b[x/2]);
            System.out.print(" ");
        }
        
        lol = b[0];
        for (x = 1; x < b.length; x++) {
            if (lol < b[x]) {
                lol = b[x];
            }
        }
        System.out.println("\nMax: "+lol);
        for (x = 0; x < b.length; x++) {
            if (lol == b[x]) {
                System.out.println("Vị trí: "+x);
            }
        }
        
        lol = b[0];
        for (x = 1; x < b.length; x++) {
            if (lol > b[x]) {
                lol = b[x];
            }
        }
        System.out.println("Min: "+lol);
        for (x = 0; x < b.length; x++) {
            if (lol == b[x]) {
                System.out.println("Vị trí: "+x);
            }
        }
        lol = 0;
        x = 0;
        for (int z = 0; z < b.length; z++) {
            lol += b[z];
            x++;
        }
        System.out.println("Tổng: "+lol);
        System.out.println("Trung bình cộng: "+(lol/x));
    }
}