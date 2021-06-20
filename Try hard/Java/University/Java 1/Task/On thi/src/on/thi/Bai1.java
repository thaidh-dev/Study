package on.thi;

import java.util.Arrays;
import java.util.Scanner;

public class Bai1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bạn muốn nhập bao nhiêu phần tử: ");
        int a = sc.nextInt();
        int s[] = new int[a];
        for (int x = 0; x < a; x++) {
            System.out.printf("Phần tử %d: ",x);
            s[x] = sc.nextInt();
        }
        System.out.println("\ns = "+Arrays.toString(s));
        System.out.print("Các phần tử ở vị trí lẻ là:");
        int max = s[1];
        int min = s[1];
        double tong = 0;
        for (int x = 1; x < a; x += 2) {
                System.out.print(" "+s[x]);
                if (min > s[x]) {
                    min = s[x];
                }
                if (max < s[x]) {
                    max = s[x];
                }
                tong += s[x];
        }
        System.out.println("\nMax của vị trí lẻ: "+max);
        for (int x = 0; x < a; x++) {
            if (s[x] == max) {
                System.out.println("Vị trí: "+x);
            }
        }
        System.out.println("Min của vị trí lẻ: "+min);
        for (int x = 0; x < a; x++) {
            if (s[x] == min) {
                System.out.println("Vị trí: "+x);
            }
        }
        System.out.println("Tổng = "+tong);
        System.out.println("Trung bình cộng = "+tong/(Math.floor(a/2)));
    }
}
