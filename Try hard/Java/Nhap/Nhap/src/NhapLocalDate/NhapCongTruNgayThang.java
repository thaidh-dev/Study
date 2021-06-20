/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NhapLocalDate;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Dương Hồng Thái
 */
public class NhapCongTruNgayThang {
    public static void main(String[] args) {
        LocalDate l = LocalDate.of(2019, 1, 1);
//        LocalDate l2 = l.plusMonths(1);
        LocalDate l2 = l.plusDays(60);
        
        System.out.println(l2);

        LocalDate l3 = LocalDate.of(2019, 1, 1);
        LocalDate l4 = LocalDate.of(2019, 2, 1);
        
        long elapsedDays = ChronoUnit.DAYS.between(l3, l4);
        System.out.println(elapsedDays);
        
        String home = System.getProperty("user.dir");
        System.out.println(home);
        
    }
    
}
