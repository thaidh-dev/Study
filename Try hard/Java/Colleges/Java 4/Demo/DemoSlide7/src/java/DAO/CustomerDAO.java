package DAO;

import fpoly.entity.Customers;
import fpoly.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class CustomerDAO {
    public static boolean checkLogin(String user, String pass) {
        List<Customers> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "from Customers where makhachhang = '" + user + "' and " + "matkhau = '" + pass + "'";
        Query query = session.createQuery(sql);
        list = query.list();
        if (list.size() > 0) {
            return true;
        }
        return false;
    }
    
    public static List<Customers> getListCustomers(String tenkh) {
        List<Customers> list = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "from Customers";
        if (tenkh.length() > 0) {
            sql += " where hoten like '%" + tenkh + "%'";
        }
        Query query = session.createQuery(sql);
        list = query.list();
        return list;
    }
    
    public static Customers getInfoCustomer(String makhachhang) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Customers kh = (Customers) session.get(Customers.class, makhachhang);
        session.close();
        return kh;
    }
    
    public static boolean insertCustomer(Customers kh) {
        if (CustomerDAO.getInfoCustomer(kh.getMaKhachHang()) != null)
        return false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(kh);
            session.getTransaction().commit();
            return true;
        } 
        catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
            return false;
        }
        finally {
            session.close();
        }
    }
    
    public static boolean updateCustomer(Customers kh) {
        if (CustomerDAO.getInfoCustomer(kh.getMaKhachHang()) != null)
            return false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.update(kh);
            session.getTransaction().commit();
            return true;
        } 
        catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
            return false;
        }
    }
    
    public static boolean deleteCustomer(String maKhachHang) {
        Customers kh = CustomerDAO.getInfoCustomer(maKhachHang);
        if (kh == null)
            return false;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction().begin();
            session.delete(kh);
            session.getTransaction().commit();
            return true;
        } 
        catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println(e);
            return false;
        }
    }
}
