package nhap;

public class DongVat {
    public String a;
    public int b;
    
    public void sendMessage(String message) {
        System.out.println(message);
    }
    
    public DongVat() {
    }
    
    public DongVat(String a, int b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
    
    
}
