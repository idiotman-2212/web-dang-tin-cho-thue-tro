package ptithcm.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ptithcm.entity.BaiVietEntity;
import ptithcm.entity.ThongBaoEntity;
import ptithcm.hibernate.HibernateUtil;

import java.util.List;

public class ThongBaoDao {
    public Integer InsertTB(ThongBaoEntity tb){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(tb);
            t.commit();
        }
        catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return 0;
        }
        finally {
            session.close();
            return 1;
        }
    }

    public Integer updateTB(ThongBaoEntity tb){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(tb);
            t.commit();
        }
        catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return 0;
        }
        finally {
            session.close();
            return 1;
        }
    }

    public List<ThongBaoEntity> getTBFromID(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            String hql = "FROM ThongBaoEntity WHERE matb = " + id;
            return session.createQuery(hql, ThongBaoEntity.class).list();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }
}
