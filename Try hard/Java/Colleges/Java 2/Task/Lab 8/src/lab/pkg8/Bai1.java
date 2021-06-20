package lab.pkg8;

import java.util.ArrayList;

public class Bai1 {
    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        al.add(7);
        al.add(7.7);
        al.add(true);
        al.add("hello");
        System.out.println(al.toString());
//        thêm 2 cách xuất list:
//        for (int i = 0; i < al.size(); i++) {
//            System.out.println(al.get(i));
//        }
//        for (Object o : al) {
//            System.out.println(o);
//        }
    }
}
