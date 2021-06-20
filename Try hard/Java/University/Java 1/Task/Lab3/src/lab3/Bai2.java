package lab3;

public class Bai2 {
    public static void main(String[] args) {
        int x;
        for (int a = 2; a < 10; a++) {
            System.out.printf("Bảng cửu chương %d\n", a );
            for (int b = 1; b < 11; b++) {
                x = a * b;
                System.out.printf("%d * %d = %d\n", a, b, x);
            }
            System.out.println("");
        }
    }
    
}
