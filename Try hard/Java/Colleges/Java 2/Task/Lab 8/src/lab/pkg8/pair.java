package lab.pkg8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class pair<T, S> {
    private T first;
    private S second;
    
    public pair(T fi, S se) {
        first = fi;
        second = se;
    }
    
    public T getFirst() {
        return first;
    }
    
    public S getSecond() {
        return second;
    }
}

class pairdemo {
    public static void main(String[] args) {
        pair<Double, String> p1 = new pair(1, "one");
        pair<String, String> p2 = new pair("hello", "one");
        System.out.println(p1.getFirst()+", "+p1.getSecond());
        System.out.println(p2.getFirst()+", "+ p2.getSecond());
        
    }
}

class examble <T extends Number> {
    private T number;
    
    examble (T n) {
        number = n;
    }
    
    double reciprocal() {
        return 1/number.doubleValue();
    }
    
    double fraction() {
        return number.doubleValue() - number.intValue();
    }

    boolean testequal (examble<?> e) {
        if (number.doubleValue() == e.number.doubleValue()) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        examble<Integer> e1 = new examble<Integer>(5);
        examble<Double> e2 = new examble<Double>(5.0);
        
        System.out.println("e1==e2"+e2.testequal(e1));
        
        List <A> listA = new ArrayList<>();
        List <B> listB = new ArrayList<B>();
        List <C> listC = new ArrayList<C>();
        A a = new A();
        B b = new B();
        C c =new C();
        processEle(listB);
        processEle(listA);
        processEle(listC);
        thai(listA);
        thai(listB);
        thai(listC);
        thai(new ArrayList<Object>());
    }
    
    public static void processEle (List<? extends A> elements) {
        System.out.println("lmao");
    }
    
    public static void thai (List<? super C> vailon) {
        System.out.println("vãi cả lồn");
    }
}


class A<T> {
    T a;
    
    
    public static void main(String[] args) {
    }
    
}

class B extends A{
    
}

class C<bolean> extends B{
}

class thai {

    public thai() {
    }
}
