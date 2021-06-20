package nhap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Nhap2 {
    static String x;
    static int a;
    static double b;
    static Nhap n;
    
    static {
        System.out.println(a);
        System.out.println(b);
    }
    
    public static void main(String[] args) {
        String y;
        System.out.println(x);
        System.out.println(a);
        System.out.println(b);
        System.out.println(n);
        
        int m[] = {10}, n = 10;
        m1(m);
        m2(n);
        System.out.println(m[0]);
        System.out.println(n);
        
        List<String> lst = new ArrayList<>();
        Set<Integer> set = new HashSet<Integer>();
    }
    
    static void m1(int x[]) {
        System.out.println(a);
        x[0] = 5;
    }
    
    static void m2(int x) {
        x = 5;
    }
    
    static int m1() {
        return 1;
    }
}
