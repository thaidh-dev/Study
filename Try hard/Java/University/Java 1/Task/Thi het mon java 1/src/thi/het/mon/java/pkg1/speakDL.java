package thi.het.mon.java.pkg1;

import java.util.Scanner;

public class speakDL {
    static Scanner sc = new Scanner(System.in);
    
    static String speakString(String message) {
        System.out.println(message);
        String input = sc.nextLine();
        return input;
    }
    
    static int speakInt(String message) {
        System.out.println(message);
        int input = Integer.parseInt(sc.nextLine());
        return input;
    }
}
