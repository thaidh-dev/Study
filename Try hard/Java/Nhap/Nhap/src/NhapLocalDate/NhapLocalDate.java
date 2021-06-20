/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhapLocalDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Dương Hồng Thái
 */
public class NhapLocalDate {

    public static void main(String[] args) {
        LocalDate a1 = LocalDate.of(2020, 3, 1);
        LocalDate b1 = LocalDate.of(2020, 5, 1);

        LocalDate a2 = LocalDate.of(2020, 6, 1);
        LocalDate b2 = LocalDate.of(2020, 7, 1);

        if ((a1.isBefore(b2) || a1.isEqual(b2)) && (b1.isAfter(a2) || b1.isEqual(a2))) {
            System.out.println("Đè vào nhau");
            return;
        }

        System.out.println("Không đè vào nhau");
        
        System.out.println(LocalDate.now());
        
        System.out.println(LocalDate.of(2018, 2, 1).plusDays(60));
//        if (a1.isBefore(null)) {
//            System.out.println("alo");
//        }


        System.out.println(LocalDate.parse("20-05-2020", DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        
        String xxx = "17-06-2020 23-06-2020";
        System.out.println(Arrays.toString(xxx.split(" ")));
        
        
        LocalDate aa = LocalDate.of(2020, 3, 1);
        LocalDate bb = LocalDate.of(2020, 3, 1);
        System.out.println(aa.isEqual(bb));
    }
}
