package ptithcm.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ptithcm.entity.TaiKhoanEntity;
import ptithcm.hibernate.HibernateUtil;

public class TaiKhoanDao {
    public Integer UpdateAccount(TaiKhoanEntity tk){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.update(tk);
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
