package TestBuffered;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            System.out.println("Client đang connect...");
            Socket socket = new Socket("localhost", 1111);

            System.out.println("Client đã connect");

            BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            dos.write("7");
            dos.newLine();
            dos.flush();
            dos.append("3");
            dos.newLine();
            dos.flush();

            System.out.println("Tổng = " + dis.readLine());

            socket.close();
            dis.close();
            dos.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
