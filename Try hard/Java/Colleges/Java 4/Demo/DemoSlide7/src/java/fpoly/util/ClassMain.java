/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpoly.util;

import fpoly.entity.Customers;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ClassMain {
    public static void main(String[] args) {
        Customers kh = new Customers("KH011", "123", "trung professor", "thaidh@gmail.com", "0989898789");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();
            String sql = "from Customers";
            Query query = session.createQuery(sql);
            List<Customers> lst = query.list();
            for (Customers c : lst) {
                System.out.println(c.getHoTen());
            }
            //HibernateUtil.getSessionFactory().close();
        } 
        catch (Exception e) {
            System.out.println(e);
            HibernateUtil.getSessionFactory().close();
        }
    }
}
