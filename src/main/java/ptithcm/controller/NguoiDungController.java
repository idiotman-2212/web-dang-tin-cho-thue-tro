package ptithcm.controller;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import ptithcm.dao.BaiVietDao;
import ptithcm.dao.NguoiDungDao;
import ptithcm.entity.*;
import ptithcm.service.UserService;
@Controller
@RequestMapping("/nguoidung")
public class NguoiDungController {
    @Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserService userService ;

	@RequestMapping("/dangbai")
	public String dangbaiget(ModelMap model)
	{
		NguoiDungDao userDao = new NguoiDungDao();
		String username= userService.currentUserName();
		TaiKhoanEntity tk= userDao.findByUserName(username);
		model.addAttribute("user",tk.getNguoidung());
		return "Posts/DangBai";
	}

	@RequestMapping("/update-post/{id}")
	public String getPostUpdate(ModelMap model,@PathVariable("id") Long id)
	{
		NguoiDungDao userDao = new NguoiDungDao();
		BaiVietDao bVietDao =new BaiVietDao();
		String username= userService.currentUserName();

		TaiKhoanEntity tk = userDao.findByUserName(username);
		if(tk != null) {
			model.addAttribute("user",tk.getNguoidung());
		}
		List<BaiVietEntity> b = bVietDao.getById(id);
		if(b.size() > 0){
			model.addAttribute("post",b.get(0));
			model.addAttribute("video", b.get(0).getChitietbaiviet().getLinkVideo());
		}

		Collection<AnhEntity> images = b.get(0).getAnh();
		if(images.size()>0){
			model.addAttribute("images", images);
		}
		model.addAttribute("id",id);
		return "Posts/UpdatePost";
	}

    @RequestMapping(value = "/addnew", method = RequestMethod.GET)
	public @ResponseBody String dangbai (HttpServletRequest request)
	{
		String TieuDe= request.getParameter("TieuDe");
		String MoTa= request.getParameter("MoTa");
		String SDT= request.getParameter("SDT");
		String Gia= request.getParameter("Gia");
		String DienTich= request.getParameter("DienTich");
		String Tinh= request.getParameter("Tinh");
		String Huyen= request.getParameter("Huyen");
		String Xa= request.getParameter("Xa");
		System.out.print(TieuDe);
		return Tinh;
	}

    @RequestMapping("/index")
	public String QuanLiBaiViet(ModelMap model,HttpServletRequest request){
	  NguoiDungDao userDao = new NguoiDungDao();
	  String username= userService.currentUserName();
	  List<BaiVietEntity> bviet = userDao.getAllBaiviet(username);
	  int page= ServletRequestUtils.getIntParameter( request,"p",0);
        PagedListHolder pagedListHolder =new PagedListHolder(bviet);
        pagedListHolder.setPage(page);
        pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
	  model.addAttribute("BaiViet",pagedListHolder);
	  model.addAttribute("UserName",username);
      return "user/quanlybaidang";
	}

	@RequestMapping("/trangcanhan")
	public String trangcanhan (ModelMap model){
	NguoiDungDao userDao = new NguoiDungDao();
	String username= userService.currentUserName();
	TaiKhoanEntity tk= userDao.findByUserName(username);

	NguoiDungEntity user = tk.getNguoidung();
	List<ThongBaoEntity> notiList = (List<ThongBaoEntity>) user.getThongbao();
	model.addAttribute("user",user);
	model.addAttribute("acc",tk);
		System.out.println(notiList.size());
	if(notiList.size() > 0){
			model.addAttribute("notiList", notiList);
		}
	model.addAttribute("notiCount",notiList.size());
	List<BaiVietEntity> bviet= (List<BaiVietEntity>) tk.getNguoidung().getBaiviet();
	int tatca=0;
	int chuaduyet=0;
	int danghienthi=0;
	int hethan=0;
	int an = 0;

	Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	for(BaiVietEntity i: bviet){
		tatca++;
		if(i.isDisplay()) danghienthi++;
		if(!i.getTinhtrang()) chuaduyet++;
		if(i.getChitietbaiviet().getThoigianketthuc().compareTo(timestamp) < 0) hethan++;
		if(i.getAn()) an++;
	}
	model.addAttribute("tatca",tatca);
	model.addAttribute("postList",bviet);
	model.addAttribute("chuaduyet",chuaduyet);
	model.addAttribute("danghienthi",danghienthi);
	model.addAttribute("hethan",hethan);
	model.addAttribute("an",an);
       return "user/trangcanhan";
	}
	@RequestMapping(value="/dangxuat",method=RequestMethod.GET)
	public String dangxuat(){
		return"Layout/logout";
	}
}

