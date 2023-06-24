package ptithcm.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ptithcm.dao.BaiVietDao;
import ptithcm.dao.NguoiDungDao;
import ptithcm.entity.BaiVietEntity;
import ptithcm.entity.TaiKhoanEntity;
import ptithcm.service.UserService;
@Controller
@RequestMapping("/baiviet")
public class BaiVietController {
    boolean isFilter = false,isFilterByProvince = false,isFilterByOption = false,isFilterByPrice = false,isFilterByArea = false;
    String provinceGl,districtGl,priceFromGl,priceToGl,areaFromGl,areaToGl,provinceIsoGl,filterOption,priceFromIsoGl,priceToIsoGl,areaFromIsoGl,areaToIsoGl;
    @Autowired
    private UserService userService;
    @RequestMapping("/chitiet/{id}")
    public String chitiet(ModelMap model,@PathVariable("id") Long id){
        BaiVietDao bVietDao =new BaiVietDao();
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        if(tk != null) {
            model.addAttribute("user",tk.getNguoidung());
        }
        List<BaiVietEntity> b = bVietDao.getById(id);
        BaiVietEntity post = b.get(0);
        model.addAttribute("baiviet",post);
        if(post.getChitietbaiviet().getLinkVideo() != null){
            model.addAttribute("linkvideo",post.getChitietbaiviet().getLinkVideo());
        }else{
            System.out.println("video is null!");
        }
        List<BaiVietEntity> list = bVietDao.getRelatePost(post.getChitietbaiviet().getTinhtp());
        for(int i = 0;i<list.size();i++){
            if(list.get(i).getMabaiviet().equals(post.getMabaiviet())){
                list.remove(i);
            }
        }
        model.addAttribute("relatePost",list);
        return "Posts/DetailPage";
    }
    @RequestMapping("/index")
    public String index(ModelMap model,HttpServletRequest request){
        BaiVietDao bVietDao =new BaiVietDao();
        NguoiDungDao userDao = new NguoiDungDao();
        List<BaiVietEntity> list = bVietDao.getAll();
        if(isFilterByOption){
            list = bVietDao.sortPost(filterOption);
        }
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        if(tk != null) {
            model.addAttribute("user",tk.getNguoidung());
        }
        int page= ServletRequestUtils.getIntParameter( request,"p",0);
        PagedListHolder pagedListHolder =new PagedListHolder(list);
        pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
        model.addAttribute("baiviet", pagedListHolder);
        return "Posts/Home";
    }
    @RequestMapping(value = "/post-filter",method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getFilterValue(HttpServletRequest request)throws IOException {
        JSONObject data= new JSONObject(request.getParameter("filterValues"));
        if(data!=null){
            isFilter = true;
            provinceGl = data.getString("tinh");
            districtGl = data.getString("huyen");
            priceFromGl = data.getString("giaTu");
            priceToGl = data.getString("giaDen");
            areaFromGl = data.getString("dienTichTu");
            areaToGl = data.getString("dienTichDen");
        }
        return "success";
    }
    @RequestMapping(value = "/post-filter-by-province",method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getFilterProvinceValue(HttpServletRequest request)throws IOException {
        JSONObject data= new JSONObject(request.getParameter("province"));
        if(data!=null){
            isFilterByProvince = true;
            provinceIsoGl = data.getString("province");
            System.out.println(provinceIsoGl);
            return "1";
        }
        return "0";
    }

    @RequestMapping(value = "/post-filter-by-price",method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getFilterPriceValue(HttpServletRequest request)throws IOException {
        JSONObject data= new JSONObject(request.getParameter("price"));
        if(data!=null){
            isFilterByPrice = true;
            priceFromIsoGl = data.getString("priceFrom");
            priceToIsoGl = data.getString("priceTo");
            return "1";
        }
        return "0";
    }

    @RequestMapping(value = "/post-filter-by-area",method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getFilterAreaValue(HttpServletRequest request)throws IOException {
        JSONObject data= new JSONObject(request.getParameter("area"));
        if(data!=null){
            isFilterByArea = true;
            areaFromIsoGl = data.getString("areaFrom");
            areaToIsoGl = data.getString("areaTo");
            return "1";
        }
        return "0";
    }

    @RequestMapping(value = "/post-filter-by-option",method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getFilterOption(HttpServletRequest request)throws IOException {
        JSONObject data= new JSONObject(request.getParameter("filter"));
        if(data!=null){
            isFilterByOption = true;
            filterOption = data.getString("data");
            System.out.println("line 124"+filterOption);
            System.out.println("line 125"+ isFilterByOption);
            return "1";
        }
        return "0";
    }

    @RequestMapping("/index/filter")
    public String getFilterPage(ModelMap model,HttpServletRequest request){
        System.out.println("=====================================================");
        BaiVietDao bVietDao =new BaiVietDao();
        NguoiDungDao userDao = new NguoiDungDao();
        List<BaiVietEntity> list = bVietDao.getAll();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        if(tk != null) {
            model.addAttribute("user",tk.getNguoidung());
        }
        if(isFilterByOption){
            list = bVietDao.sortPost(filterOption);
            isFilterByOption =false;
        }
        if(isFilter){
            list = bVietDao.getFilterPost(provinceGl,districtGl,priceFromGl,priceToGl,areaFromGl,areaToGl);
            isFilter = false;
        }
        if(isFilterByProvince){
            list = bVietDao.getFilterPostByProvince(provinceIsoGl);
            isFilterByProvince  =false;
        }
        if(isFilterByPrice){
            list = bVietDao.getFilterPostByPrice(priceFromIsoGl,priceToIsoGl);
            isFilterByPrice = false;
        }
        if(isFilterByArea){
            list = bVietDao.getFilterPostByArea(areaFromIsoGl,areaToIsoGl);
            isFilterByArea = false;
        }

        int page= ServletRequestUtils.getIntParameter( request,"p",0);

        PagedListHolder pagedListHolder =new PagedListHolder(list);
        pagedListHolder.setPage(page);
        pagedListHolder.setMaxLinkedPages(5);
        pagedListHolder.setPageSize(5);

        model.addAttribute("baiviet", pagedListHolder);
        return "Posts/HomeFilter";
    }

}
