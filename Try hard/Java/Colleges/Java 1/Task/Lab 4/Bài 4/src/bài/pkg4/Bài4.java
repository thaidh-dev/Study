package bài.pkg4;

public class Bài4 {

    public static void main(String[] args) {
        System.out.println("BẢNG CỬU CHƯƠNG");
        System.out.println("");
        int a;
        int b;
        for (a = 2; a <= 9; a++ ) {
            System.out.printf("Bảng nhân %d", a);
            System.out.println("");
            for (b = 1; b <= 10; b++) {
                System.out.printf("%d * %d = %d", a, b, a * b);
                System.out.println("");
            }
            System.out.println("");
        }
    }
    
}
