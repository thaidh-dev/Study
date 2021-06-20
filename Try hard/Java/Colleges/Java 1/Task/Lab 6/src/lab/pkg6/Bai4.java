package lab.pkg6;

import java.util.Arrays;
import java.util.Scanner;

public class Bai4 {
    
    public static int[] swap(int a[], int m, int n) {
        int temp = a[m];
        a[m] = a[n];
        a[n] = temp;
        System.out.println("");
        System.out.print("Mảng sau khi tráo: ");
        return a;
    }
    
    public static int[] them(int a[], int x) {
        int b[] = new int[a.length + 1];
        System.arraycopy(a, 0, b, 0, a.length);
        b[a.length] = x;
        System.out.println("");
        System.out.print("Mảng sau khi thêm: ");
        return b;
    }
    
    public static int[] xoa(int a[], int x) {
        int b[] = new int[a.length - 1];
        System.arraycopy(a, 0, b, 0, x);
        System.arraycopy(a, x+1, b, x, a.length - 1 - x);
        System.out.println("");
        System.out.print("Mảng sau khi xóa: ");
        return b;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bạn muốn nhập bao nhiêu phần tử: ");
        int y = sc.nextInt();
        int a[] = new int[y];
        for (int x = 0; x < y; x++) {
            System.out.printf("Phần tử %d: ",(x+1));
            a[x] = sc.nextInt();
        }
        System.out.println("");
        System.out.print("a[] = "+Arrays.toString(a));
        System.out.print(Arrays.toString(swap(a, 2, 4)));
        System.out.print(Arrays.toString(them(a, 6)));
        System.out.print(Arrays.toString(xoa(a, 3)));
        System.out.println("");
    }
    
}
