package sample.camel;

import java.io.*;
import java.net.*;

public class ServerSocketAuth {
    public static void main(String[] args) {
        try {
            System.out.println("Server đang connect...");
            ServerSocket serverSocket = new ServerSocket(8010);
            Socket socket = serverSocket.accept();
            System.out.println("Server đã connect");

            BufferedReader dr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter dw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            String a = dr.readLine();
            System.out.println("Message server nhận đc: " + a);
            dw.append(a);
            dw.newLine();
            dw.flush();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
