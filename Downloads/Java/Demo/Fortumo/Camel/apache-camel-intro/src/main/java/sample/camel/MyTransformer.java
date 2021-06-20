package sample.camel;

public class MyTransformer {

    public String TransformContent(String body) {
        return body.toUpperCase();
    }
}
