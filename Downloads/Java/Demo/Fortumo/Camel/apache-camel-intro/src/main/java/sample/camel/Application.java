package sample.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        String fuck = "7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777";
        System.out.println(fuck);
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", "hello");
        map.put("b", "tangina mo");
        map.put("a", "bobo");
        map.put("c", "cyka");

        double random = Math.round(Math.random() * 8999999 + 1000000);
        String xxx = String.valueOf(random).substring(0, String.valueOf(random).length() - 2);
        System.out.println(xxx);
//6
        System.out.println(Math.pow(10, 7) - Math.pow(10, 6) - 1 + " alo");

        System.out.println(map.size());

        try {
//            Thread.sleep(5000);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        System.out.println("haizzzzzzzzzzzzzzz");
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.getTime());

        double a = Math.random();
        double b = Math.random();
        System.out.println(a);
        System.out.println(b);
        if (a < 0) {
            System.out.println("thái");
        } else if (b < 1) {
            System.out.println("nam");
        } else if (b > 0) {
            System.out.println("haizzzzzzzzz");
        } else {
            System.out.println("sai");
        }

        System.out.println(random(7) + " alo, đồng bào có nghe rõ không");

    }

    private static String random(double soChuSo) {
        double randomCorrelationId = Math.round(Math.random() * (Math.pow(10, soChuSo) - Math.pow(10, soChuSo - 1) - 1) + Math.pow(10, soChuSo - 1));
        String correlationId = String.valueOf(randomCorrelationId);
        String correlationIdSubString = correlationId.substring(0, correlationId.length() - 2);

        return correlationIdSubString;
    }


//    public static void main(String[] args) {
//        try {
//            Main main = new Main();
//            File kestoreFile = new File("paymentgateway.jks");
//            main.bind("password", "paymentgateway");
//            main.bind("ksf", kestoreFile);
//            main.bind("tsf", kestoreFile);
//
//            CamelContext camelContext = main.getOrCreateCamelContext();
//            ProducerTemplate pc = camelContext.createProducerTemplate();
//            MyRouterBuilder routerBuilder = new MyRouterBuilder(pc);
//            camelContext.addRoutes(routerBuilder);
//
//            camelContext.start();
//
//            Thread.sleep(5 * 60 * 1000);
//            camelContext.stop();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
