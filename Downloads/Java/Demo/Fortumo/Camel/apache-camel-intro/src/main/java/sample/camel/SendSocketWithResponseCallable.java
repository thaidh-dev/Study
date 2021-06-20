package sample.camel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Created by nghiatm on 4/15/2018.
 */
public class SendSocketWithResponseCallable implements Callable<String> {
    String host;
    int port;
    String data;
    int timeout;
    public Socket socket = null;
    public BufferedReader d = null;
    Writer out;

    public SendSocketWithResponseCallable(String host, int port, String data, int timeout) {
        this.host = host;
        this.port = port;
        this.data = data;
        this.timeout = timeout;
    }

    public String call() throws Exception {
        String responseLine;

        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), timeout);
            socket.setSoTimeout(timeout);

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            d = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.append(data);
            out.flush();

            responseLine = d.readLine();
            System.out.println("responseLine: " + responseLine);

            out.close();
            d.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
            if (out != null)
                out.close();
            if (d != null)
                d.close();
            if (socket != null)
                socket.close();
            throw e;
        }
        return responseLine;
    }
}
