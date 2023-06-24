package ptithcm.dao;
import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ptithcm.entity.BaiVietEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.TaiKhoanEntity;
import ptithcm.entity.TenQuyenEntity;
import ptithcm.hibernate.HibernateUtil;
public class NguoiDungDao {
    public TaiKhoanEntity findByUserName(String user_name){
        TaiKhoanEntity tk= new TaiKhoanEntity();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql="from TaiKhoanEntity AS tk where  tk.tenDN = :name";
            tk = session.createQuery(hql,TaiKhoanEntity.class).setParameter("name", user_name).uniqueResult();
            return tk;
        } catch(Exception e){
            return null;
        }
    }
	public NguoiDungEntity findById(Long id){
		NguoiDungEntity tk = new NguoiDungEntity();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql="from NguoiDungEntity AS tk where  tk.maND = :id";
			tk = session.createQuery(hql,NguoiDungEntity.class).setParameter("id",id).uniqueResult();
			return tk;
		} catch(Exception e){
			return null;
		}
	}
	//retrun 2 bị trùng tên đăng nhập ,1 thành công, 0 thất bại

	public Integer insertTk (TaiKhoanEntity tk) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		if(this.findByUserName(tk.getTenDN())!=null)
			return 2;
		try {
			tk.setTinhtrang(true);
			tk.setQuyen(this.getTenQuyen(2));
			session.save(tk);
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
	public Integer insert (NguoiDungEntity nguoiDung) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {

			session.save(nguoiDung);
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

	public int updateUser(NguoiDungEntity user){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
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

    public TenQuyenEntity getTenQuyen(Integer id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		String hql = "FROM TenQuyenEntity where maquyen =:id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		TenQuyenEntity list = (TenQuyenEntity) query.list().get(0);
		return list;
	}
    public Long getMaND(String username){
		TaiKhoanEntity tk = this.findByUserName(username);
        if(tk!=null){
          return tk.getNguoidung().getMaND();
		}
     return 0L;
	}
	public List<BaiVietEntity> getAllBaiviet(String username){
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql ="FROM BaiVietEntity where nguoidung.maND=:id";
			Query query = session.createQuery(hql);
			query.setParameter("id",this.getMaND(username));
			 return (List<BaiVietEntity>) query.list();
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		finally{
			session.close();
		}
  
	}

	public List<BaiVietEntity> getPostByID(String PostID) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			String hql = "FROM BaiVietEntity where mabaiviet = " + PostID;
			Query query = session.createQuery(hql);
			return (List<BaiVietEntity>) query.list();

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Integer checkEmailSdt(String Email, String Sdt){
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql ="FROM NguoiDungEntity as nguoidung where nguoidung.sdt=:sdt or nguoidung.email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("sdt",Sdt);
			query.setParameter("email",Email);
			return query.list().isEmpty()? 1:2;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		finally{
			session.close();
		}
	}
	public NguoiDungEntity findUserByEmail(String email)
	{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql ="FROM NguoiDungEntity as nguoidung where nguoidung.email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("email",email);
			return  (NguoiDungEntity) query.list().get(0);

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		finally{
			session.close();
		}
	}
	public NguoiDungEntity findUserByTokenReset(String token)
	{
		Session session=HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			String hql ="select nguoidung FROM PasswordResetTokenEntity as pas where pas.token=:token";
			Query query = session.createQuery(hql);
			query.setParameter("token",token);
			return  (NguoiDungEntity) query.list().get(0);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		finally{
			session.close();
		}
	}
}
