package ptithcm.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ptithcm.entity.BaiVietEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.TaiKhoanEntity;
import ptithcm.entity.TenQuyenEntity;
import ptithcm.hibernate.HibernateUtil;

public class BaiVietDao {
    public List < BaiVietEntity > getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("from BaiVietEntity as Bv where Bv.tinhtrang=1 and Bv.an=0", BaiVietEntity.class).list();
        }
		catch(Exception e){
		return null;
	}
		finally{
		session.close();
	}
	}

	public List < BaiVietEntity > getAllForParticularUser(String id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "FROM BaiVietEntity WHERE MaND = " + id;
			return session.createQuery(hql, BaiVietEntity.class).list();
		}
		catch(Exception e){
			System.out.println("Insert Post Failure !");
			return null;
		}
		finally{
			session.close();
		}

	}

    public List<BaiVietEntity> getById(Long id) {
		Session session= HibernateUtil.getSessionFactory().openSession();
        try  {
            String hql="from BaiVietEntity where mabaiviet = "+ id;
            Query query = session.createQuery(hql);
            return query.list();
        }
		catch(Exception e){
			return null;
		}
		finally{
			session.close();
		}
    }
	public List<BaiVietEntity> getRelatePost(String province) {
		Session session= HibernateUtil.getSessionFactory().openSession();

		try  {
			String hql="from BaiVietEntity where chitietbaiviet.tinhtp = ?1";
			Query query =  session.createQuery(hql);
			query.setParameter(1,province);
			query.setFirstResult(0);
			query.setMaxResults(4);
			return query.list();
		}
		catch(Exception e){
			return null;
		}
		finally{
			session.close();
		}
	}

	public List<BaiVietEntity> sortPost(String option) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		try  {
			String hql="from BaiVietEntity";
			switch(option){
				case "1":
					break;
				case "2":
					hql+= " order by chitietbaiviet.thoigianbatdau DESC";
					break;
				case "3":
                    hql +=" where chitietbaiviet.linkVideo is not null";
					break;
				default:
					System.out.println("option is invalid?");
			}
			System.out.println(hql);
			Query query = session.createQuery(hql);
			return query.list();
		}
		catch(Exception e){
			return null;
		}
		finally{
			session.close();
		}

	}
	public List<BaiVietEntity> getFilterPost(
			 String province
			,String district
			,String priceFrom
			,String priceTo
			,String areaFrom
			,String areaTo
	) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		try  {
			boolean isProvinceFilter = false;
			boolean isDistrictFilter = false;

			String hql="from BaiVietEntity where";

			if(!priceTo.equals("0")){
				hql+=" gia >= "+ priceFrom + " and gia <= " + priceTo;
			}

			if(!areaTo.equals("0") && !priceTo.equals("0")){
				hql+=" and dientich >= " + areaFrom + " and dientich <= " + areaTo;
			}

			if(!areaTo.equals("0") && priceTo.equals("0")){
				hql+=" dientich >= " + areaFrom + " and dientich <= " + areaTo;
			}

			if(priceTo.equals("0") && areaTo.equals("0")){
				if(province.length() > 0){
					isProvinceFilter = true;
					hql = hql + " chitietbaiviet.tinhtp = ?1";

					if (district.length() > 0) {
						isDistrictFilter = true;
						hql = hql + " and chitietbaiviet.quanhuyen = ?2";
					}
				}else{
					if (district.length() > 0) {
						isDistrictFilter = true;
						hql = hql + "chitietbaiviet.quanhuyen = ?2";
					}
				}
			}else{
				if(province.length() > 0){
					isProvinceFilter = true;
					hql = hql + " and chitietbaiviet.tinhtp = ?1";
				}
				if (district.length() > 0) {
					isDistrictFilter = true;
					hql = hql + " and chitietbaiviet.quanhuyen = ?2";
				}
			}

			Query query = session.createQuery(hql);
			if(isProvinceFilter){
				query.setParameter(1,province);
			}
			if(isDistrictFilter){
				query.setParameter(2,district);
			}

			System.out.println(hql);
			return query.list();
		}
		catch(Exception e){
			return null;
		}
		finally{
			session.close();
		}
	}

	public List<BaiVietEntity> getFilterPostByProvince(String province) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		try  {
			String hql="from BaiVietEntity where chitietbaiviet.tinhtp = ?1";
			Query query = session.createQuery(hql);
			query.setParameter(1,province);
			return query.list();
		}
		catch(Exception e){
			return null;
		}
		finally{
			session.close();
		}
	}

	public List<BaiVietEntity> getFilterPostByPrice(String startPrice,String endPrice) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		try  {
			String hql="from BaiVietEntity where gia >= "+ startPrice + " and gia <= " + endPrice;
			System.out.println(hql);
			Query query = session.createQuery(hql);
			return query.list();
		}
		catch(Exception e){
			return null;
		}
		finally{
			session.close();
		}
	}

	public List<BaiVietEntity> getFilterPostByArea(String startArea,String endArea) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		try  {
			String hql="from BaiVietEntity where dientich >= "+ startArea + " and dientich <= " + endArea;
			Query query = session.createQuery(hql);
			return query.list();
		}
		catch(Exception e){
			return null;
		}
		finally{
			session.close();
		}
	}

	public  int UpdateBaiViet (BaiVietEntity bv){
		Session session = HibernateUtil.getSessionFactory().openSession();
		BaiVietEntity updatePost = (BaiVietEntity) session.merge(bv);
		Transaction t = session.beginTransaction();
		try {
			session.update(updatePost);
			t.commit();
			return 1;
		}
		catch (Exception e) {
			e.printStackTrace();
			t.rollback();
			return 0;
		}
		finally {
			session.close();
			System.out.println(" Update Post Finally!");
		}
	}

    public Integer insertBaiViet (BaiVietEntity bv) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		try {
			bv.setAn(Boolean.FALSE);
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
	public NguoiDungEntity getNguoidung(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
	session.beginTransaction();
	String hql = "FROM NguoiDungEntity where maND =:id";
	Query query = session.createQuery(hql); query.setParameter("id", id); 
	NguoiDungEntity list = (NguoiDungEntity) query.list().get(0); 
	return list;

	}
		catch(Exception e){
		return null;
	}
		finally{
		session.close();
	}}
	public boolean SetAn(BaiVietEntity bv){
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		boolean a= !bv.getAn();
		bv.setAn(a);
		try {  
		   session.update(bv);
           t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			return false;
		}
		finally{
			session.close();
		}
		return true;
	}
	 
}
