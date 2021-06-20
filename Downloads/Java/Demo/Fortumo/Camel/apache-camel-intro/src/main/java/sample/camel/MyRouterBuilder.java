package sample.camel;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;

public class MyRouterBuilder extends RouteBuilder {
    ProducerTemplate pc;

    public MyRouterBuilder(ProducerTemplate pc) {
        this.pc = pc;
    }

//?move=./done
    public void configure() throws Exception {
        from("netty-http:https://0.0.0.0:8088/auth?sync=true&ssl=true&passphrase=#password&keyStoreFile=#ksf&trustStoreFile=#tsf")
                .process(new MyLogProcessor(pc));
//                .bean(new MyTransformer(), "TransformContent");
//                .process(new MyLogProcessor())
//                .to("file:/G:/NghiemTuc/Study/Java/Fortumo/Camel/apache-camel-intro/tmp/output");

//        from("netty:tcp://localhost:8088/auth?sync=true&ssl=true&passphrase=#password&keyStoreFile=#ksf&trustStoreFile=#tsf")
//                .process(new MyLogProcessor());
//                .bean(new MyTransformer(), "TransformContent");
//                .process(new MyLogProcessor())
//                .to("file:/G:/NghiemTuc/Study/Java/Fortumo/Camel/apache-camel-intro/tmp/output");
    }
}
