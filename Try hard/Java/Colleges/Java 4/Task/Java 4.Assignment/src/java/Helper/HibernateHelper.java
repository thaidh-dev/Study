package Helper;

import Hibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class HibernateHelper<T> {
    static Session session;
    
    public static void begin() {
        try {
            session = HibernateUtil.getSessionFatory().getCurrentSession();
            session.beginTransaction();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    static {
        begin();
    }
    
    public List<T> query(String sql, Object...args) {
        try {
            Query query = session.createQuery(sql);
            for (int i = 0; i < args.length; i++) {
                query.setParameter(i, args[i]);
            }
            return query.list();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void update(T t) {
        try {
            session.update(t);
            session.getTransaction().commit();
            begin();
        }
        catch (Exception e) {
            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
                System.out.println(e);
            }
        }
    }
}
