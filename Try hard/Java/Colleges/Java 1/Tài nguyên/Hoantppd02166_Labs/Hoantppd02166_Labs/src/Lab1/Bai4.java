package Lab1;
import java.util.Scanner;
public class Bai4 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Họ và tên: ");
        String hovaten = scan.nextLine();
        System.out.println("Điểm trung bình: ");
        Double diemTB = scan.nextDouble();
        System.out.println("Họ và tên: " + hovaten);
        System.out.printf("Điểm trung bình: %.2f \n" , diemTB);
    }
    
}