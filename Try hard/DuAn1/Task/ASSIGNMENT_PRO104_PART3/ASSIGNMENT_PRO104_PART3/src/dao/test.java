/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import helper.DateHelper;
import java.util.List;
import object.DocGia;

/**
 *
 * @author Thanh
 */
public class test {

    public static void main(String[] args) {
        String now = DateHelper.toString(DateHelper.now(), "ddMMyyyy");
        System.out.println(now);

        List<DocGia> list;
        list = new DocGiaDAO().select();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getTheDG());
            System.out.println(list.get(i).getTenDG());
        }
    }
}
