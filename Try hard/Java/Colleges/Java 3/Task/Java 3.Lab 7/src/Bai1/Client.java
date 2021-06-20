package Bai1;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client đang connect...");
            Socket socket = new Socket("localhost", 1111);

            System.out.println("Client đã connect");
            
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            Scanner sc = new Scanner(System.in);
            
//            while (true) {
                System.out.print("Nhập a: ");
                double a = sc.nextDouble();
                System.out.print("Nhập b: ");
                double b = sc.nextDouble();
                sc.nextLine();

                dos.writeDouble(a);
                dos.flush();
                dos.writeDouble(b);
                dos.flush();

                System.out.println("Tổng = " + dis.readDouble());
//                System.out.print("Bạn có muốn nhập tiếp không (y/n): ");
//                if (sc.nextLine().equals("n")) {
//                    break;
//                }
//            }
            
            socket.close();
            dis.close();
            dos.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
