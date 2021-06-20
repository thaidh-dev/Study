
package Lab7_hoantppd02166;
import java.util.Scanner;
public class Bai1 {
    public static void main(String[] args) {
    Scanner Sc = new Scanner(System.in);
        System.out.println("Nhap ten: ");
    String hoten = Sc.nextLine();
    int i = hoten.indexOf(" ");
    String ho = hoten.substring(0, i);
    System.out.println("Ho: " + ho);
    int j = hoten.lastIndexOf(" ");
    String ten = hoten.substring(j);
        System.out.println("Ten" + ten);
    String holot = hoten.substring(i, j);
        System.out.println("holot: " + holot);
}
    }
    
