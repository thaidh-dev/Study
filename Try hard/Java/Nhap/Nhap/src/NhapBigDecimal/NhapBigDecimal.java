/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhapBigDecimal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dương Hồng Thái
 */
public class NhapBigDecimal {

    public static void main(String[] args) {
        BigDecimal b = new BigDecimal(434343);
        BigDecimal.valueOf(48849384);
        
        String a = abc(false);
        System.out.println(a);
        
        List<String> lst = new ArrayList<>();
        lst.add("a");
        lst.add("b");
        lst.add("c");
        lst.add("d");
        System.out.println(lst.toString());
        
        String xxx = "abc";
        if (xxx.equals(null)) {
            System.out.println("Thái");
        }
    }

    public static String abc(boolean a) {
        if (a) throw new RuntimeException("alo");
        
        return "haizz";
    
    }

}
