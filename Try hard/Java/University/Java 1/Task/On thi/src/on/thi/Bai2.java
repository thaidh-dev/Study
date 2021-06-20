package on.thi;

import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Bạn muốn nhập bao nhiêu hàng và cột: ");
        int hc = sc.nextInt();
        int a[][] = new int[hc][hc];
        int tong = 0;
        int tongchan = 0;
        int maxhang[] = new int[hc];
        int minhang[] = new int[hc];
        int tonghang[] = new int[hc];
        int tonghangchan[] = new int[hc];
        
        for (int x = 0; x < hc; x++) {
            for (int y = 0; y < hc; y++) {
                System.out.printf("Nhập hàng %d cột %d: ", x, y);
                a[x][y] = sc.nextInt();
            }
        }
        
        int max = a[0][0];
        int min = a[0][0];
        System.out.println("\nIn ma trận:");
        for (int x = 0; x < hc; x++) {
            maxhang[x] = a[x][0];
            minhang[x] = a[x][0];
            for (int y = 0; y < hc; y++) {
                System.out.print(a[x][y]+" ");
                tong += a[x][y];
                tonghang[x] += a[x][y];
                if (a[x][y] % 2 == 0) {
                    tongchan += a[x][y];
                    tonghangchan[x] += a[x][y];
                }
                if (max < a[x][y]) {
                    max = a[x][y];
                }
                if (min > a[x][y]) {
                    min = a[x][y];
                }
                if (maxhang[x] < a[x][y]) {
                    maxhang[x] = a[x][y];
                }
                if (minhang[x] > a[x][y]) {
                    minhang[x] = a[x][y];
                }
            }
            System.out.println("");
        }
        
        System.out.println("\nMa trận tam giác dưới trái");
        for (int x = 0; x < hc; x++) {
            for (int y = 0; y < x+1; y++) {
                System.out.print(a[x][y]+" ");
            }
            System.out.println("");
        }
        
        System.out.println("\nMa trận tam giác trên trái");
        for (int x = 0; x < hc; x++) {
            for (int y = 0; y < hc - x; y++) {
                System.out.print(a[x][y]+" ");
            }
            System.out.println("");
        }
        
        System.out.println("\nTổng các phần tử = "+tong);
        System.out.println("Tổng các phần tử chẵn = "+tongchan);
        System.out.println("Tổng các phần tử lẻ = "+(tong - tongchan));
        System.out.println("");
        
        System.out.println("Max của ma trận = "+max);
        System.out.println("Min của ma trận = "+min);
        System.out.println("");
        
        for (int x = 0; x < hc; x++) {
            System.out.printf("Max hàng %d = %d\t", x, maxhang[x]);
            for (int y = 0; y < hc; y++) {
                if (a[x][y] == maxhang[x]) {
                    System.out.printf("Vị trí: a[%d][%d]\t", x, y);
                }
            }
            System.out.printf("Min hàng %d = %d\t", x, minhang[x]);
            for (int y = 0; y < hc; y++) {
                if (a[x][y] == minhang[x]) {
                    System.out.printf("Vị trí: a[%d][%d]\t", x, y);
                }
            }
            System.out.println("");
        }
        System.out.println("");
        
        for (int x = 0; x < hc; x++) {
            System.out.printf("Tổng hàng %d = %d\t", x, tonghang[x]);
            System.out.printf("Tổng các phần tử chẵn của hàng %d = %d\t", x, tonghangchan[x]);
            System.out.printf("Tổng các phần tử lẻ của hàng %d = %d\n", x, (tonghang[x] - tonghangchan[x]));
        }
    }
}
