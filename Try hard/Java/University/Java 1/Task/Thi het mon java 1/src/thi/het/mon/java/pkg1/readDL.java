package thi.het.mon.java.pkg1;

import java.util.Scanner;

public class readDL {
    static Scanner sc = new Scanner(System.in);
    static float a[];
    static float b[];
    static float c[];
    
    public static void main(String[] args) {
        menu();
    }
    
    static void menu() {
        int chon;
        do {
            System.out.println("MENU");
            System.out.println("1. Nhập");
            System.out.println("2. In ra");
            System.out.println("3. Sắp xếp giảm dần");
            System.out.println("4. In ra phần tử ở vị trí chẵn");
            System.out.println("5. Tìm và in ra min của phần tử ở vị trí lẻ và vị trí của nó");
            System.out.println("6. Thêm vào cuối mảng");
            System.out.println("7. Xóa");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn 1 chức năng: ");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    yeucau1();
                    break;
                case 2:
                    yeucau2();
                    break;
                case 3:
                    yeucau3();
                    break;
                case 4:
                    yeucau4();
                    break;
                case 5:
                    yeucau5();
                    break;
                case 6:
                    yeucau6();
                    break;
                case 7:
                    yeucau7();
                    break;
                case 0:
                    System.out.println("Thoát");
                    System.exit(0);
                default:
                    System.out.println("\nHãy chọn từ 0 đến 7\n");
            }
        }
        while (chon != 0);
    }
    
    static void yeucau1() {
        System.out.println("\nNHẬP\n");
        System.out.print("Bạn muốn nhập bao nhiêu phần tử: ");
        int so = sc.nextInt();
        a = new float[so];
        for (int x = 0; x < so; x++) {
            System.out.printf("Nhập phần tử %d: ", x);
            a[x] = sc.nextFloat();
        }
        System.out.println("");
    }
    
    static void yeucau2() {
        System.out.println("\nXUẤT\n");
        System.out.print("a =");
        for (int x = 0; x < a.length; x++) {
            System.out.print(" "+a[x]);
        }
        System.out.println("\n");
    }
    
    static void yeucau3() {
        System.out.println("\nSẮP XẾP GIẢM DẦN\n");
        for (int x = 0; x < a.length - 1; x++) {
            for (int y = x+1; y < a.length; y++) {
                if (a[x] < a[y]) {
                    float temp = a[x];
                    a[x] = a[y];
                    a[y] = temp;
                }
            }
        }
    }
    
    static void yeucau4() {
        System.out.println("\nCÁC PHẦN TỬ Ở VỊ TRÍ CHẴN\n");
        System.out.print("a =");
        for (int x = 0; x < a.length; x += 2) {
                System.out.print(" "+a[x]);
        }
        System.out.println("\n");
    }
    
    static void yeucau5() {
        System.out.println("\nTìm và in ra min của phần tử ở vị trí lẻ và vị trí của nó\n");
        float min = a[1];
        for (int x = 1; x < a.length; x += 2) {
            if (min > a[x]) {
                min = a[x];
            }
        }
        System.out.println("Min = "+min);
        for (int x = 1; x < a.length; x += 2) {
            if (min == a[x]) {
                System.out.println("Vị trí: "+x);
            }
        }
        System.out.println("");
    }
    
    static void yeucau6() {
        System.out.println("\nTHÊM\n");
        System.out.print("Nhập: ");
        int s = sc.nextInt();
        b = new float[a.length + 1];
        System.arraycopy(a, 0, b, 0, a.length);
        b[a.length] = s;
        a = b;
        System.out.println("");
    }
    
    static void yeucau7() {
        System.out.println("\nXÓA\n");
        System.out.print("Vị trí cần xóa: ");
        int s = sc.nextInt();
        c = new float[a.length - 1];
        System.arraycopy(a, 0, c, 0, s);
        System.arraycopy(a, s+1, c, s, a.length - s - 1);
        a = c;
        System.out.println("");
    }

}
