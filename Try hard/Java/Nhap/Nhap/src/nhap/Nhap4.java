package nhap;

public class Nhap4 {
    static int a;
    Integer b = 7;
    
    public static void main(String[] args) {
        MyInner.MyInner1 mi1 = new MyInner.MyInner1();
        MyInner.MyInner2 mi2 = new MyInner().new MyInner2();
    }

    public static void main() {
        Nhap3 n = new Nhap3();
        
        System.out.println(n.hashCode());
    }
    
    public void x() {
        System.out.println(a);
    }
    
    
    
}
