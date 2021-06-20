package TestDaTaSteam;

import Bai1.*;
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

            dos.writeDouble(7);
            dos.flush();
            dos.writeDouble(3);
            dos.flush();

            System.out.println("Tổng = " + dis.readDouble());

            socket.close();
            dis.close();
            dos.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
