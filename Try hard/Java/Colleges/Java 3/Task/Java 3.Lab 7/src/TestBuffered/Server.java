package TestBuffered;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            System.out.println("Server đang connect...");
            ServerSocket serverSocket = new ServerSocket(1111);
            Socket socket = serverSocket.accept();
            System.out.println("Server đã connect");

            BufferedReader dis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));

            String a = dis.readLine();
            String b = dis.readLine();

            dos.append(String.valueOf(Integer.parseInt(a) + Integer.parseInt(b)));
            dos.newLine();
            dos.flush();

//            socket.close();
//            dis.close();
//            dos.close();

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
