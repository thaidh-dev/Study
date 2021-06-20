package Helper;


import hibernate.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

public class HibernateHelper<T> {
    static Session session;
        
    public static void begin() {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
        } 
        catch (HibernateException e) {
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

    public void delete(String entity, int id) {
        try {
            T t = get(entity, id);
            session.delete(t);
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
    
    public void insert(T t) {
        try {
            session.save(t);
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
    
    public T get(String entity, Object o) {
        T t = (T) session.get(entity, (Serializable) o);
        return t;
    }
}
