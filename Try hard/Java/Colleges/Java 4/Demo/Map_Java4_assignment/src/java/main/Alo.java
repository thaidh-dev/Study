package main;

import hibernate.entity.NguoiDung;
import hibernate.entity.SanPham;
import hibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class Alo {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            String sql = "from NguoiDung";
            Query query = session.createQuery(sql);
            List<NguoiDung> lst = query.list();
            
            for (NguoiDung nd : lst) {
                System.out.println(nd.getEmail());
            }
        }
        catch (Exception e) {
            System.out.println("");
        }
        finally {
            session.close();
        }
        
            Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            System.out.println("");
            
            session2.beginTransaction();
            Query query2 = session2.createQuery("from SanPham");
            List<SanPham> lst2 = query2.list();
            for (SanPham sp : lst2) {
                System.out.println(sp.getTenSanPham());
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            session2.close();
            session.getSessionFactory().close();
        }
    }
}
