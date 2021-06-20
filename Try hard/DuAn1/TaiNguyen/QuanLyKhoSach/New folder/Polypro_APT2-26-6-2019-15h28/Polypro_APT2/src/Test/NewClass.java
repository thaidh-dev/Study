/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import DAO.KhoaHocDAO;
import Helper.DateHelper;
import Model.KhoaHoc;
import java.io.File;
import java.sql.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSI
 */
public class NewClass {
    public static void main(String[] args) {
        KhoaHoc kh = new KhoaHoc(1, "COM12", 1000, 30, "1-7-2019", "", "17-6-2019", false);
        
        if (flagNgayHH(kh)) {
            System.out.println("hết hạn");
        } else {
            System.out.println("chưa hết hạn");
        }
    }
    public static boolean flagNgayHH(KhoaHoc khoaHoc) {
        DateHelper dateHelper = new DateHelper();
        int time = khoaHoc.getThoiLuong();
        int day = time / 2;
        int numberDay = (day / 3) * 7 + day % 3;

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateHelper.toDate(khoaHoc.getNgayKG()));
            calendar.add(Calendar.DAY_OF_YEAR, numberDay);
            Date HH = calendar.getTime();

            if (new Date().after(HH)) {
                return true;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    
}
