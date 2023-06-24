package ptithcm.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ptithcm.entity.AnhEntity;
import ptithcm.entity.ChiTietBaiVietEntity;
import ptithcm.hibernate.HibernateUtil;

public class AnhDao {
    public  Integer Insert(AnhEntity bv){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(bv);
            t.commit();
        }
        catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return 0;
        }
        finally {
            session.close();
            System.out.println("Insert Image Finally!");
        }
        return 1;
    }
    public  Integer Delete(AnhEntity img){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.delete(img);
            t.commit();
        }
        catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return 0;
        }
        finally {
            session.close();
        }
        return 1;
    }
}
