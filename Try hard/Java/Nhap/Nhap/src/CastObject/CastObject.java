package CastObject;

public class CastObject {

    public static void main(String[] args) {
        Object o = new Object();
        A a = new A();
        a.setA("hello");
        a.setB("xin chào");
        o = a;
        B b = (B) o;
        
        System.out.println(b.getM());
    }
}

class A {

    String a;
    String b;

    public A() {
    }

    public A(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}

class B {

    String m;
    String n;

    public B() {
    }

    public B(String m, String n) {
        this.m = m;
        this.n = n;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
}
