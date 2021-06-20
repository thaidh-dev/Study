package helper;

import hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class HibernateHelper<T> {
    private static Session session;

    public static void begin() {
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
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
        Query query = session.createQuery(sql);

        for (int i = 0; i < args.length; i++) {
            query.setParameter(i+1, args[i]);
        }

        return query.list();
    }

    public void saveOrUpdate(T t) {
        try {
            session.saveOrUpdate(t);
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

    public T get(String entity, int id) {
        T t = (T) session.get(entity, id);
        return t;
    }
}
