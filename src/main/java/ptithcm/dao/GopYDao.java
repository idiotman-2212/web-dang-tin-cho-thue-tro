package ptithcm.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ptithcm.entity.GopYEntity;
import ptithcm.entity.ThongBaoEntity;
import ptithcm.hibernate.HibernateUtil;

public class GopYDao {
    public Integer InsertGY(GopYEntity feedback){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            session.save(feedback);
            t.commit();
        }
        catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return 0;
        }
        finally {
            session.close();
            System.out.println("Insert feedback close session !");
        }
        return 1;
    }
}
