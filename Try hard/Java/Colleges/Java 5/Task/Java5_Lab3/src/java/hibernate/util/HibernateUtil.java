package hibernate.util;

import model.Nganh;
import model.SinhVien;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Nganh.class).addAnnotatedClass(SinhVien.class).buildSessionFactory();
        }
        catch (HibernateException e) {
            System.out.println(e);
            throw new ExceptionInInitializerError();
        }
    }

    public static SessionFactory getSessionFatory() {
        return sessionFactory;
    }
}
