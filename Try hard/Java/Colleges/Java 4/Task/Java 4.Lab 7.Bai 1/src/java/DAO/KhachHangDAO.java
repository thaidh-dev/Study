package DAO;

import fpoly.entity.KhachHang;
import fpoly.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class KhachHangDAO {

    public static List<KhachHang> select() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "from KhachHang";
        Query query = session.createQuery(sql);
        List<KhachHang> lstKhachHang = query.list();
        session.close();
        return lstKhachHang;
    }

    public static List<KhachHang> find(String ten, String sdt) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "from KhachHang where ten = :ten or sdt = :sdt";
        Query query = session.createQuery(sql);
        query.setParameter("ten", ten);
        query.setParameter("sdt", sdt);
        List<KhachHang> lstKhachHang = query.list();
        session.close();

        for (KhachHang kh : lstKhachHang) {
            System.out.println(kh.getMaKhachHang());
        }

        return lstKhachHang;
    }

    public static void insert(String hoTen, String email, String matKhau, String sdt) {
        KhachHang kh = new KhachHang();
        kh.setTen(hoTen);
        kh.setEmail(email);
        kh.setMatKhau(matKhau);
        kh.setSdt(sdt);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.beginTransaction();

            session.save(kh);
            session.getTransaction().commit();
        } 
        catch (Exception ex) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            System.out.println(ex);
        }
    }
    
    public static void update(int maKhachHang, String hoTen, String email, String matKhau, String sdt) {
        KhachHang kh = new KhachHang();
        kh.setMaKhachHang(maKhachHang);
        kh.setTen(hoTen);
        kh.setEmail(email);
        kh.setMatKhau(matKhau);
        kh.setSdt(sdt);
        
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        try {
            session.beginTransaction();
            session.update(kh);
            session.getTransaction().commit();
        } 
        catch (Exception e) {
            System.out.println(e);
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        
    }
    
    public static void delete(int maKH) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        try {
            session.beginTransaction();
            KhachHang kh = (KhachHang) session.load(KhachHang.class, maKH);
            session.delete(kh);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            System.out.println(ex);
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
    }
}
