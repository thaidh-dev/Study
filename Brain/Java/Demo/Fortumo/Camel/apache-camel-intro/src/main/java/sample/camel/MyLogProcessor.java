package sample.camel;

import com.google.gson.Gson;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ExecutorService;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.netty.http.NettyHttpComponent;
import org.apache.camel.component.netty.http.NettyHttpMessage;
import org.bson.types.ObjectId;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.joda.time.DateTime;
import org.openide.util.MapFormat;
import org.slf4j.Logger;

public class MyLogProcessor implements Processor {
    ExecutorService authPool;

    public MyLogProcessor(ProducerTemplate producerTemplate) throws Exception {
        authPool = producerTemplate.getCamelContext().getExecutorServiceManager().newThreadPool(this, "authPool", 16, 100);
    }

    public void process(Exchange exchange) throws Exception {

        String rawRequest = "";
        NettyHttpMessage nettyHttpMessage = exchange.getIn(NettyHttpMessage.class);
        if (nettyHttpMessage != null) {
            HttpRequest request = nettyHttpMessage.getHttpRequest();
            ChannelBuffer content = request.getContent();
            content.toString(Charset.forName("UTF-8"));
        } else if (exchange.getIn().getHeader("nRawRequest") != null) {
            rawRequest = (String) exchange.getIn().getHeader("nRawRequest");
        }

        String transId = UUID.randomUUID().toString();
        System.out.println("transId: " + transId);
        Customer customer = null;
        ResponseObj responseObj;
        int httpCode;
        Gson gson = new Gson();
        try {
            String msisdn = exchange.getIn().getHeader("msisdn") != null ? exchange.getIn().getHeader("msisdn").toString() : "";
            System.out.println("msisdn: " + msisdn);
            DateTime startProcessTime = new DateTime();

            String corelationId = exchange.getIn().getHeader("corelationId") != null ? exchange.getIn().getHeader("corelationId").toString() : "";
            System.out.println("corelationId: " + corelationId);

            long timestamp = Calendar.getInstance().getTimeInMillis();
            if (exchange.getIn().getHeader("timestamp") != null) {
                if (exchange.getIn().getHeader("timestamp").toString().length() > 0) {
                    timestamp = Long.parseLong(exchange.getIn().getHeader("timestamp").toString());
                    System.out.println("timestamp: " + timestamp);
                }
            }

            if (timestamp > 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(timestamp);
            }

            String pId = exchange.getIn().getHeader("pId") != null ? exchange.getIn().getHeader("pId").toString() : "";
            System.out.println("pId: " + pId);

            int price = 0;
            if (exchange.getIn().getHeader("price") != null) {
                if (exchange.getIn().getHeader("price").toString().length() > 0) {
                    price = Integer.parseInt(exchange.getIn().getHeader("price").toString());
                    System.out.println("price: " + price);
                }
            }

            String item = exchange.getIn().getHeader("item") != null ? exchange.getIn().getHeader("item").toString() : "";
            System.out.println("item: " + item);

            String store = exchange.getIn().getHeader("store") != null ? exchange.getIn().getHeader("store").toString() : "";
            System.out.println("store: " + store);

            String storeTransId = exchange.getIn().getHeader("storeTransId") != null ? exchange.getIn().getHeader("storeTransId").toString() : "";
            System.out.println("storeTransId: " + storeTransId);

            SocketMessage requestMessage = new SocketMessage();
            requestMessage.setMessage("hello, how are you");
            SocketMessage responseMessage = SyncQueueRequest.socketSendWithResponse(authPool, "localhost", 8010, requestMessage, 50000);

//            if (responseMessage.getMessage() != null) {
//                exchange.getOut().setBody("OK");
//                System.out.println("OK");
//                exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 200);
//                return;
//            }
//            exchange.getOut().setBody("FAIL");
//            System.out.println("FAIL");
//            exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
        } catch (Exception e) {
            System.out.println("Lỗi: " + e);
        }
    }
}
