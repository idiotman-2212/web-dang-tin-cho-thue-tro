package ptithcm.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.PasswordResetTokenEntity;
import ptithcm.hibernate.HibernateUtil;

public class PasswordResetTokenDAO {
    public boolean Insert(PasswordResetTokenEntity passwordResetToken){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try{
            session.save(passwordResetToken);
            t.commit();
        }
        catch (Exception ex){
            System.out.println(ex);
            t.rollback();
            return false;
        }
        finally {
            return true;
        }

    }
    public PasswordResetTokenEntity FindByToken(String token) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            String hql =" FROM PasswordResetTokenEntity as pas where pas.token=:token";
            Query query = session.createQuery(hql);
            query.setParameter("token",token);
            return  (PasswordResetTokenEntity) query.list().get(0);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        finally{
            session.close();
        }

    }
}
