
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author moon
 */
public class keyboard {
    static Scanner moon = new Scanner(System.in);
//input

    public static String readString(String message) {
        System.out.print(message);
        String input = moon.nextLine();
        return input;
    }

    public static boolean check(String a, double b, String c, String[] hoten,int i) {
        boolean kq = true;
        if (a.length() == 0) {
            System.out.println("    *Error: Họ và tên bị trống");
            kq = false;
        }
        for (int j = 0; j < i; j++) {
            if (a.equalsIgnoreCase(hoten[j])) {
                System.out.println("    *Error: Trùng tên đã nhập");
                kq=false;
                break;
            }
        }
        String reEmail="\\w+@\\w+(.\\w+)";
        if(!c.matches(reEmail)){
            System.out.println("    *Error: Không đúng dạng email");
            kq=false;
        }
        if(b>10||b<0){
            System.out.println("    *Error: Điểm nhập vào cần lớn 0 và nhỏ hơn 10");
            kq=false;
        }
        if(kq==false){
            System.out.println("    Yêu cầu nhập lại!");
        }
        return kq;
    }

    public static int readInt(String message) {
        System.out.print(message);
        int input = moon.nextInt();
        moon.nextLine();
        return input;
    }

    public static double readDouble(String message) {
        System.out.print(message);
        double input = moon.nextDouble();
        moon.nextLine();
        return input;
    }

    public static boolean readBoolean(String message) {
        System.out.print(message);
        boolean input = moon.nextBoolean();
        return input;
    }

}
    

