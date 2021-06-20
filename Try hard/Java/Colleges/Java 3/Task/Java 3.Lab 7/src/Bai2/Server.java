package Bai2;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocketA = new ServerSocket(1111);
            ServerSocket serverSocketB = new ServerSocket(2222);
            Socket socketA = serverSocketA.accept();
            Socket socketB = serverSocketB.accept();
            
            DataInputStream disA = new DataInputStream(socketA.getInputStream());
            DataOutputStream dosA = new DataOutputStream(socketA.getOutputStream());
            DataInputStream disB = new DataInputStream(socketB.getInputStream());
            DataOutputStream dosB = new DataOutputStream(socketB.getOutputStream());
            
            new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            dosB.writeUTF(disA.readUTF());
                        }
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            dosA.writeUTF(disB.readUTF());
                        }
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }.start();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
