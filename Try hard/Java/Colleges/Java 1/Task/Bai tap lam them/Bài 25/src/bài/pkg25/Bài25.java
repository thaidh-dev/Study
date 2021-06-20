package bài.pkg25;

public class Bài25 {

    public static void main(String[] args) {
        int x;
        int a;
        int b;
        int c;
        System.out.println("Các số cần tìm là: ");
        for (x = 100; x <= 999; x++) {
            for (a = 1; a <= 9; a++) {
                for (b = 0; b <= 9; b++) {
                    for (c = 0; c <= 9; c++) {
                        if (x == Math.pow(a, 3) + Math.pow(b, 3) + Math.pow(c, 3) && x == a * 100 + b * 10 + c) {
                            System.out.println(""+x);
                        }
                    }
                }
            }
        }
    }
}