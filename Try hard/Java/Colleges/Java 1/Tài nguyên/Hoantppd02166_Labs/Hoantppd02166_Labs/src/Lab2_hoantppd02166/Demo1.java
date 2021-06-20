package Lab2;
import java.util.Scanner;
public class Demo1 {
    static final double in=2.54;
    public static void main(String[] args) {
         Scanner Sc = new Scanner(System.in);
         System.out.print("Nhập vào 1 số: ");
                  double cm = Sc.nextDouble();
                  double inch = cm / in;
         System.out.printf("Giá trị đã chuyển sang Inch: %.2f \n" , inch);
    }  
}
