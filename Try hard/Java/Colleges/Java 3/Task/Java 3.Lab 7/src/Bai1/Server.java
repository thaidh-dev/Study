package Bai1;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Server đang connect...");
            ServerSocket serverSocket = new ServerSocket(1111);
            Socket socket = serverSocket.accept();
            System.out.println("Server đã connect");
            
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
//            while (true) {
                double a = dis.readDouble();
                double b = dis.readDouble();
                
                dos.writeDouble(a+b);
//            }
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
