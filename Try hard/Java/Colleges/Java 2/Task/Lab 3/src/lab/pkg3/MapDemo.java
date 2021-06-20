package lab.pkg3;

import java.util.*;
public class MapDemo {
    
    public static void main(String[] args) {
        Map <String, Student> map = new HashMap <>();
        
        Student sv = new Student();
        sv.ten = "Tuấn";
        sv.nganh = "CNTT";
        sv.diem = 7;
        map.put(sv.ten, sv);
        
        Set <String> keys = map.keySet();
        for (String name : keys) {
            Student sv1 = map.get(name);
            System.out.println("Họ và tên: "+sv.ten);
            System.out.println("Học lực: "+sv.hocLuc());
        }
    }
}

class thai{
    String a;
    int b;
    thai(String a, int b) {
        this.a = a;
        this.b = b;
    }
    
    thai() {
        
    }
    @Override
    public boolean equals(Object anObject) {
        if (anObject instanceof thai) {
            thai another = (thai) anObject;
            if (this.a.equals(another.a) &&
                this.b == another.b) {
                return true;
            }
        }
        return false;
    }
}
class a {
    public static void main(String[] args) {
        List<thai> list = new ArrayList();
        thai a = new thai("7",2);
        thai a2 = new thai("2",2);
        thai a3 = new thai("6", 2);
        thai a4 = new thai("4",2);
        thai a5 = new thai("9",2);
        list.add(a);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
        
        thai a6 = new thai("4", 3);
        int index = list.indexOf(a6);
        System.out.println(index);
        for (int i = 0; i < list.size(); i++) {
            thai lmao = list.get(i);
            if (a6.a.equals(list.get(i).a)) {
                System.out.println("vãi cả lồn");
            }
            else {
                System.out.println("h sao");
            }
        }
    }
}

class b {
    public static void main(String[] args) {
        int x;
        List list = new ArrayList();
        do {
        Scanner sc = new Scanner(System.in);
        x = sc.nextInt();
        if (x == 2) {
        list.add(6);
        }
        if (x == 3) {
            list.add(7);
        }
        if (x == 4) {
            list.add(9);
        }
        if (x == 5) {
            list.add(3);
        }
        if (x == 6) {
            list.add(1);
        }
        }
        while (x != 0);
        System.out.println(list.get(10));
    }
}