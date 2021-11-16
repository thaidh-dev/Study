package sample.camel;

import com.google.gson.Gson;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.*;
import org.xsocket.connection.BlockingConnection;
import org.xsocket.connection.IBlockingConnection;

/**
 * Created by NghiaTM on 8/11/2016.
 */
public class SyncQueueRequest {
    public static final String HEADER_SYNC_REQUEST_ID = "SYNC_REQUEST_ID";
    public static final String SEDA_PREFIX = "jms:";

    public static SocketMessage socketSendWithResponse(String host, int port, SocketMessage messageObj, int timeout) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageObj.toJson());
        stringBuilder.append("\r\n");

        String resultString = null;
        SocketMessage result = null;

        try {
            final IBlockingConnection bc = new BlockingConnection(host, port);
            bc.setIdleTimeoutMillis(timeout);
            bc.setReadTimeoutMillis(timeout);
            bc.setConnectionTimeoutMillis(timeout);
            bc.write(stringBuilder.toString());


            resultString = bc.readStringByDelimiter("\r\n");
            bc.close();


            if (resultString != null) {
                resultString = resultString.replace("\n", "");
                resultString = resultString.replace("\r", "");
                Gson gson = new Gson();
                result = gson.fromJson(resultString, SocketMessage.class);

            }

        } catch (IOException e) {
            e.printStackTrace();
            result = new SocketMessage();
            result.setStatus(-1);
            result.setException(e);
        }


        return result;
    }

    public static SocketMessage socketSendWithResponse(ExecutorService executor, String host, int port, SocketMessage messageObj, int timeout) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(messageObj.toJson());
        stringBuilder.append("\r\n");

        String resultString = null;
        SocketMessage result = null;

        try {
            System.out.println("stringBuilder: " + stringBuilder.toString());
            Callable<String> task = new SendSocketWithResponseCallable(host, port, stringBuilder.toString(), timeout);
            Future<String> future = executor.submit(task);
//            try {
//                resultString = future.get(timeout, TimeUnit.MILLISECONDS);
//                System.out.println("resultString: " + resultString);
//            } catch (Exception e) {
//                System.out.println(e);
//                result = new SocketMessage();
//                result.setStatus(-1);
//                result.setException(e);
//            }
//
//            if (resultString != null) {
//                resultString = resultString.replace("\n", "");
//                resultString = resultString.replace("\r", "");
//                Gson gson = new Gson();
//                result = gson.fromJson(resultString, SocketMessage.class);
//
//            } else {
//
//            }

        } catch (Exception e) {
            System.out.println("địt mẹ mày, thằng wibi này " + e);
            result = new SocketMessage();
            result.setStatus(-1);
            result.setException(e);
        }

        return result;
    }
}
