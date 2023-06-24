package ptithcm.controller;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ptithcm.dao.BaiVietDao;
import ptithcm.dao.NguoiDungDao;
import ptithcm.entity.BaiVietEntity;
import ptithcm.entity.GopYEntity;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.TaiKhoanEntity;
import ptithcm.entity.ThongBaoEntity;
import ptithcm.hibernate.HibernateUtil;
import ptithcm.service.UserService;

@Controller
@Transactional
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	private UserService userService;
    SessionFactory factory;
    Session session;
    //thông kê
    @RequestMapping(value = "tongquan", method = RequestMethod.GET)
    public String tongQuan(HttpServletRequest request, ModelMap model) {
		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
		}else{
			System.out.println("Account is not found!");
		}

		return"Admin/tongquan";
    }
    //biểu đồ
    @RequestMapping(value = "bieudo", method = RequestMethod.GET)
    public String bieuDo(HttpServletRequest request, ModelMap model) {
		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
		}else{
			System.out.println("Account is not found!");
		}
		return"Admin/bieudo";
    }
    // thống kê số lượng bài đăng
    @RequestMapping( value ="quanlybaidang", method = RequestMethod.GET)
    public String BaiViet(HttpServletRequest request, ModelMap model) {
		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
		}else{
			System.out.println("Account is not found!");
		}
    	List<BaiVietEntity> Baivietchuaduyet = this.getBVChuaDuyet();
    	List<BaiVietEntity> Baivietdaduyet = this.getBVDaDuyet();
    	//phân trang
    	int page = ServletRequestUtils.getIntParameter(request, "p" , 0);
    	PagedListHolder pagedListHolder = new PagedListHolder(Baivietchuaduyet);
    	pagedListHolder.setPage(page);
    	pagedListHolder.setMaxLinkedPages(3);
    	pagedListHolder.setPageSize(5);
    	model.addAttribute("baivietchuaduyet", pagedListHolder);
    	
    	int page1 = ServletRequestUtils.getIntParameter(request, "p1" , 0);
    	PagedListHolder pagedListHolder1 = new PagedListHolder(Baivietdaduyet);
    	pagedListHolder1.setPage(page1);
    	pagedListHolder1.setMaxLinkedPages(3);
    	pagedListHolder1.setPageSize(10);
    	model.addAttribute("baivietdaduyet", pagedListHolder1);
    	return "Admin/quanlybaidang";
    }
    
    public List<BaiVietEntity> getBVDaDuyet(){
		try{
		session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "FROM BaiVietEntity WHERE tinhtrang = True";
    	Query query = session.createQuery(hql);
    	List<BaiVietEntity> list = query.list();
		return list;
		}
		catch (Exception e)
		{
			return null;
		}
    	finally{
			session.close();
		}
    }
    public List<BaiVietEntity> getBVChuaDuyet(){
    	try{ session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "FROM BaiVietEntity WHERE tinhtrang = False";
    	Query query = session.createQuery(hql);
    	List<BaiVietEntity> list = query.list();
    	return list;
		}
		catch(Exception e){
			return null;
		}
		finally {
			session.close();
		}
    }
    public BaiVietEntity getBaiviet(Long id) {
		try{
		session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM BaiVietEntity WHERE mabaiviet = :id and tinhtrang = False";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		BaiVietEntity list = (BaiVietEntity) query.list().get(0);
		return list;
		} 
		catch(Exception e){
          return null;
		}
		finally{
			session.close();
		}
	} 
    
    
    //xóa bài viết chưa duyệt bị phạm lỗi
    
    @RequestMapping(value = "quanlybaidang/{mabaiviet}.htm", params = "linkDelete")
    public String deleteBaiVietChuaDuyet (HttpServletRequest request, ModelMap model, @PathVariable("mabaiviet") Long mabaiviet) {
		Integer temp = this.deleteBVChuaDuyet(this.getBaiviet(mabaiviet));
		 List<BaiVietEntity> Baivietchuaduyet = this.getBVChuaDuyet();
		 List<BaiVietEntity> Baivietdaduyet = this.getBVDaDuyet();
		 int page = ServletRequestUtils.getIntParameter(request, "p" , 0); 
		 PagedListHolder pagedListHolder = new PagedListHolder(Baivietchuaduyet);
		 pagedListHolder.setPage(page); 
		 pagedListHolder.setMaxLinkedPages(3);
		 pagedListHolder.setPageSize(10); 
		 model.addAttribute("baivietchuaduyet",pagedListHolder);


		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
		}else{
			System.out.println("Account is not found!");
		}
	
		if(temp != 0) {
			model.addAttribute("message","Delete thành công");
		}
		else {
			model.addAttribute("message", "Delete không thành công");
		}
    	return "redirect:/admin/quanlybaidang.html";
    }
    public Integer deleteBVChuaDuyet( BaiVietEntity bv) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction t = session.beginTransaction();
    	try { 
			session.delete(bv);
			t.commit();
		}
		catch (Exception e){
			System.out.println(e);
			t.rollback();
			return 0;
		}
		finally{
			session.close();
		}
    	return 1;
    }
    
    // update lại trạng thái bài viết
	@RequestMapping(value = "quanlybaidang/{mabaiviet}.htm", params = "linkEdit" )
	public String edit (HttpServletRequest request, ModelMap model, 
			@PathVariable("mabaiviet") long mabaiviet) {

		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
			System.out.println(tk.getNguoidung().getTenND());
		}else{
			System.out.println("Account is not found!");
		}

		BaiVietEntity bv = this.getBaiviet(mabaiviet);
		bv.setTinhtrang(true);
		Integer temp = this.updateTrangThai(bv);
		List<BaiVietEntity> Baivietchuaduyet = this.getBVChuaDuyet();
    	model.addAttribute("baivietchuaduyet", Baivietchuaduyet);
		//model.addAttribute("baivietchuaduyet", bv);
		model.addAttribute("btnupdate","true");
		model.addAttribute("mabaiviet", mabaiviet);
		if( temp != 0) {
			model.addAttribute("message", "Duyệt thành công");
		}
		else {
			model.addAttribute("message", "Duyệt không thành công");
		}
		return "redirect:/admin/quanlybaidang.html";
	}
    
	
    public Integer updateTrangThai( BaiVietEntity bviet) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction t = session.beginTransaction();
    	try { 
			session.update(bviet);
			t.commit();
		}
		catch (Exception e){
			System.out.println(e);
			t.rollback();
			return 0;
		}
		finally{
			session.close();
		}
    	return 1;
    }
    
    // thống kê số lượng người dùng
    @RequestMapping(value = "quanlynguoidung", method = RequestMethod.GET)
    public String NguoiDung(HttpServletRequest request, ModelMap model) {
		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
		}else{
			System.out.println("Account is not found!");
		}

    	List<NguoiDungEntity> Nguoidung = this.getNguoiDung();
    	
    	int page = ServletRequestUtils.getIntParameter(request, "p" , 0);
    	PagedListHolder pagedListHolder = new PagedListHolder(Nguoidung);
    	pagedListHolder.setPage(page);
    	pagedListHolder.setMaxLinkedPages(3);
    	pagedListHolder.setPageSize(10);
    	model.addAttribute("nguoidung", pagedListHolder);
    	return"Admin/quanlynguoidung";
    }
    
    public List<NguoiDungEntity> getNguoiDung() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "FROM NguoiDungEntity ";
    	Query query = session.createQuery(hql);
    	List<NguoiDungEntity> list = query.list();
    	return list;
	}
   
   // show góp ý
    @RequestMapping(value = "GopY", method = RequestMethod.GET)
    public String gopY(HttpServletRequest request, ModelMap model) {
    	List<GopYEntity> Gopy = this.getGopY();

		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
		}else{
			System.out.println("Account is not found!");
		}
    	
    	int page = ServletRequestUtils.getIntParameter(request, "p" , 0);
    	PagedListHolder pagedListHolder = new PagedListHolder(Gopy);
    	pagedListHolder.setPage(page);
    	pagedListHolder.setMaxLinkedPages(3);
    	pagedListHolder.setPageSize(10);
    	model.addAttribute("gopy", pagedListHolder);
    	return"Admin/GopY";
    }
    
    public List<GopYEntity> getGopY() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "FROM GopYEntity ";
    	Query query = session.createQuery(hql);
    	List<GopYEntity> list = query.list();
    	return list;
	}
    
 // quản lý thông báo
    @RequestMapping(value = "ThongBao", method = RequestMethod.GET)
    public String thongBao(HttpServletRequest request, ModelMap model) {
    	List<ThongBaoEntity> Thongbao = this.getThongBao();

		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
		}else{
			System.out.println("Account is not found!");
		}
    	
    	int page = ServletRequestUtils.getIntParameter(request, "p" , 0);
    	PagedListHolder pagedListHolder = new PagedListHolder(Thongbao);
    	pagedListHolder.setPage(page);
    	pagedListHolder.setMaxLinkedPages(3);
    	pagedListHolder.setPageSize(10);
    	model.addAttribute("thongbao", pagedListHolder);
    	return"Admin/ThongBao";
    }
    
    public List<ThongBaoEntity> getThongBao() {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	String hql = "FROM ThongBaoEntity ";
    	Query query = session.createQuery(hql);
    	List<ThongBaoEntity> list = query.list();
    	return list;
	}
    
   //Gửi thông báo cho người dùng qua email
    @Autowired
   
    JavaMailSender mailer;
    @RequestMapping(value ="ThongBao",method = RequestMethod.POST )
    public String guiMail(HttpServletRequest request,ModelMap model,
			@RequestParam("from") String from,
			@RequestParam("to") String to,
			@RequestParam("subject") String subject,
			@RequestParam("body") String body) {
    	System.out.println(subject);
    	List<ThongBaoEntity> Thongbao = this.getThongBao();
    	
    	int page = ServletRequestUtils.getIntParameter(request, "p" , 0);
    	PagedListHolder pagedListHolder = new PagedListHolder(Thongbao);
    	pagedListHolder.setPage(page);
    	pagedListHolder.setMaxLinkedPages(3);
    	pagedListHolder.setPageSize(10);
    	model.addAttribute("thongbao", pagedListHolder);
    	try {
    		MimeMessage mail= mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			mailer.send(mail);
			model.addAttribute("message", "Gửi email thành công");
    	}
    	catch(Exception e) {
    		model.addAttribute("message","Gửi email thất bại");
    	}
    	return "Admin/ThongBao";
    }
    
}
