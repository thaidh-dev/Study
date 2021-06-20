package lab.pkg6;

import java.util.Scanner;

public class Bai2lol {
    Scanner sc = new Scanner(System.in);
    
    String nhapString(String message) {
        System.out.print(message);
        String input = sc.nextLine();
        return input;
    }
    
    double nhapDouble(String message) {
        System.out.print(message);
        double input = Double.parseDouble(sc.nextLine());
        return input;
    }
}
