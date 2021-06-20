package Hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
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
