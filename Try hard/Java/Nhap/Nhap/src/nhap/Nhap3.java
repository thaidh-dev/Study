package nhap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Nhap3 extends Nhap4 {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        String[] m = {"ggwp", "glhf", "noob"};
        String[] n = {"angular", "cyka", "bobo"};
        for (int i = 0; i < m.length; i++) {
            map.put(m[i], n[i]);
        }

        HashMap<String, String> map2 = new HashMap<>();
        String[] m2 = {"alo", "olo", "blo"};
        String[] n2 = {"mid", "bot", "top"};
        for (int i = 0; i < m2.length; i++) {
            map2.put(m2[i], n2[i]);
        }

        HashMap<String, String> map3 = new HashMap<>();
        String[] m3 = {"rofl", "lmao", "lol"};
        String[] n3 = {"lmfao", "haha", "wkwkwk"};
        for (int i = 0; i < m3.length; i++) {
            map3.put(m3[i], n3[i]);
        }

        LinkedList<HashMap> lst = new LinkedList<>();
        lst.add(map);
        lst.add(map3);
        lst.add(map2);

        System.out.println(lst);

    }

}
