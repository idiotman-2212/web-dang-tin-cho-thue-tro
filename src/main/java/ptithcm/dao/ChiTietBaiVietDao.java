package ptithcm.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ptithcm.entity.BaiVietEntity;
import ptithcm.entity.ChiTietBaiVietEntity;
import ptithcm.hibernate.HibernateUtil;

import java.util.List;

public class ChiTietBaiVietDao {
    Session session;
    public  Integer Insert(ChiTietBaiVietEntity bv){
        session = HibernateUtil.getSessionFactory().openSession();
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
        }
        return 1;
    }

    public  Integer Update(ChiTietBaiVietEntity bv){
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(bv);
            t.commit();
            return 1;
        }
        catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return 0;
        }
        finally {
            session.close();
        }
    }

}
