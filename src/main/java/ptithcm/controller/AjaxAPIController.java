package ptithcm.controller;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ptithcm.Object.User;
import ptithcm.dao.*;
import ptithcm.entity.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.UUID;


import ptithcm.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/api")
public class AjaxAPIController {

    String province_global_variable;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    ServletContext context;
    @Autowired
    UserService userService ;
    private Session session;
    public String getFileExtension(String fileName){
        String filelEx;
        int dotIndex = fileName.lastIndexOf(".");
        return fileName.substring(dotIndex);
    }

    public String writeFile(MultipartFile fileParam, String currentPath) throws IOException {
        UUID uniqueKey = UUID.randomUUID();
        byte[] bytes = fileParam.getBytes();
        String fileExtension = getFileExtension(fileParam.getOriginalFilename());
        Path path = Paths.get(context.getRealPath("//Storage//" + currentPath + "//")  + uniqueKey +  fileExtension);
        Files.write(path, bytes);
        return uniqueKey +fileExtension;
    }

    @RequestMapping("/dangky")
    public String getForm(){
        return "/user/signup-page";
    }

    @RequestMapping(value = "/post-upload", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getThongTin(
            @RequestParam("images") MultipartFile[] files,
            @RequestParam("video") MultipartFile video,
            HttpServletRequest req
    ) throws IOException {
        JSONObject data= new JSONObject(req.getParameter("info"));
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        NguoiDungEntity currentUser = tk.getNguoidung();

        System.out.println(tk.getQuyen().getTenquyen());

        List<AnhEntity> anh =new ArrayList<>();
        BaiVietEntity bv =new BaiVietEntity();
        ChiTietBaiVietEntity ct= new ChiTietBaiVietEntity();
        BaiVietDao bvD =new BaiVietDao();
        bv.setTieude(data.getString("title"));
        bv.setDiachi(data.getString("street"));
        bv.setDientich(Integer.valueOf(data.getString("area")));
        bv.setTinhtrang(Boolean.FALSE);
        if(tk.getQuyen().getTenquyen().equals("ADMIN")){
            bv.setTinhtrang(Boolean.TRUE);
        }
        Double roundPrice  = (double) Math.round( Float.valueOf(data.getString("price")) * 10) / 10;
        bv.setGia(Float.valueOf(String.valueOf(roundPrice)));
        ct.setMota(data.getString("description"));

        System.out.println(data.getString("description"));

        ct.setPhuongxa(data.getString("wards"));
        ct.setQuanhuyen(data.getString("district"));
        ct.setTinhtp(data.getString("province"));
        ct.setLinkVideo("Storage/Videos/"+ writeFile(video,"Videos"));
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Timestamp timestampEnd = new Timestamp(date.getTime());
        ct.setThoigianbatdau(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestampEnd);
        cal.add(Calendar.DAY_OF_WEEK, 15);
        timestampEnd.setTime(cal.getTime().getTime());
        ct.setThoigianketthuc(timestampEnd);
        bv.setNguoidung(currentUser);
        int result = bvD.insertBaiViet(bv);
        if(result == 1){
            List <BaiVietEntity> postList =  bvD.getAllForParticularUser(currentUser.getMaND().toString());

            BaiVietEntity currentPost = postList.get(postList.size()-1);

            AnhDao anhDao =new AnhDao();
            ChiTietBaiVietDao ctbv= new ChiTietBaiVietDao();

            ct.setMabaiviet(currentPost.getMabaiviet());
            ct.setBaiviet(currentPost);

            for(MultipartFile file: files){
                AnhEntity a = new AnhEntity();
                a.setLinkanh("Storage/Images/"+ writeFile(file,"Images"));
                a.setBaiviet(currentPost);
                anhDao.Insert(a);
                anh.add(a);
            }
            ctbv.Insert(ct);
        }else{
            System.out.println("Lỗi insert bài viết mới !");
            return "0";
        }
        return data.toString();
    }

    @RequestMapping(value = "/post-upload-no-video", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String Test(@RequestParam("images") MultipartFile[] files,HttpServletRequest req) throws IOException {
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        NguoiDungEntity currentUser = tk.getNguoidung();
        List<AnhEntity> anh =new ArrayList<>();
        JSONObject data= new JSONObject(req.getParameter("info"));
        BaiVietEntity bv =new BaiVietEntity();
        ChiTietBaiVietEntity ct= new ChiTietBaiVietEntity();
        BaiVietDao bvD =new BaiVietDao();
        bv.setTieude(data.getString("title"));
        bv.setDiachi(data.getString("street"));
        bv.setDientich(Integer.valueOf(data.getString("area")));
        bv.setTinhtrang(Boolean.FALSE);
        if(tk.getQuyen().getTenquyen().equals("ADMIN")){
            bv.setTinhtrang(Boolean.TRUE);
        }
        Double roundPrice  = (double) Math.round( Float.valueOf(data.getString("price")) * 10) / 10;
        bv.setGia(Float.valueOf(String.valueOf(roundPrice)));
        ct.setMota(data.getString("description"));

        System.out.println(data.getString("description"));

        ct.setPhuongxa(data.getString("wards"));
        ct.setQuanhuyen(data.getString("district"));
        ct.setTinhtp(data.getString("province"));
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Timestamp timestampEnd = new Timestamp(date.getTime());
        ct.setThoigianbatdau(timestamp);
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestampEnd);
        cal.add(Calendar.DAY_OF_WEEK, 15);
        timestampEnd.setTime(cal.getTime().getTime());
        ct.setThoigianketthuc(timestampEnd);
        bv.setNguoidung(currentUser);
        int result = bvD.insertBaiViet(bv);
        if(result == 1){
            List <BaiVietEntity> postList =  bvD.getAllForParticularUser(currentUser.getMaND().toString());
            BaiVietEntity currentPost = postList.get(postList.size()-1);
            AnhDao anhDao =new AnhDao();
            ChiTietBaiVietDao ctbv= new ChiTietBaiVietDao();
            ct.setMabaiviet(currentPost.getMabaiviet());
            ct.setBaiviet(currentPost);
            for(MultipartFile file: files){
                AnhEntity a = new AnhEntity();
                a.setLinkanh("Storage/Images/"+ writeFile(file,"Images"));
                a.setBaiviet(currentPost);
                anhDao.Insert(a);
                anh.add(a);
            }
            ctbv.Insert(ct);

        }else{
            System.out.println("Error !");
        }
        return data.toString();
    }

    @RequestMapping(value = "/post-upload-update/{id}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String updatePost(
            @RequestParam("images") MultipartFile[] files,
            @RequestParam("video") MultipartFile video,
            HttpServletRequest req,
            @PathVariable("id") Long id
    ) throws IOException {
        JSONObject data = new JSONObject(req.getParameter("info"));
        BaiVietDao PostDao = new BaiVietDao();
        ChiTietBaiVietDao PostDetailDao = new ChiTietBaiVietDao();
        NguoiDungDao userDao = new NguoiDungDao();
        AnhDao imgDao = new AnhDao();

        List<BaiVietEntity> listPost = userDao.getPostByID(id.toString());
        BaiVietEntity targetPost = listPost.get(0);
        String imageIDs = data.getString("imageIDs").trim();
        String[] imageIDList = imageIDs.split("_");
        if(targetPost != null){
            targetPost.setTieude(data.getString("title"));
            targetPost.setDiachi(data.getString("street"));
            targetPost.setDientich((int)Float.parseFloat(data.getString("area")));
            targetPost.setTinhtrang(false);
            Double roundPrice  = (double) Math.round( Float.valueOf(data.getString("price")) * 10) / 10;
            targetPost.setGia(Float.valueOf(String.valueOf(roundPrice)));
            System.out.println(Float.valueOf(String.valueOf(roundPrice)));
            ChiTietBaiVietEntity targetPostDetail = targetPost.getChitietbaiviet();
            targetPostDetail.setMota(data.getString("description"));
            targetPostDetail.setPhuongxa(data.getString("wards"));
            targetPostDetail.setQuanhuyen(data.getString("district"));
            targetPostDetail.setTinhtp(data.getString("province"));
            targetPostDetail.setLinkVideo("Storage/Videos/"+ writeFile(video,"Videos"));
            int updatePostResult = PostDao.UpdateBaiViet(targetPost);
            //  successfully update post will return 1
            if(updatePostResult == 1){
                int updatePostDetailResult = PostDetailDao.Update(targetPostDetail);
                System.out.println("Update Post Detail Result !" + updatePostDetailResult);
                Collection<AnhEntity> imageList = targetPost.getAnh();
                ArrayList <AnhEntity> imageListCV= new ArrayList<>(imageList);
                for(AnhEntity img:imageListCV){
                    if(!Arrays.toString(imageIDList).contains(img.getMaanh().toString())){
                        imgDao.Delete(img);
                    }
                }
                if(files.length > 0){
                    for(MultipartFile file: files){
                        AnhEntity a = new AnhEntity();
                        a.setLinkanh("Storage/Images/"+ writeFile(file,"Images"));
                        a.setBaiviet(targetPost);
                        imgDao.Insert(a);
                        System.out.println("Write image File");
                    }
                }

            }else{
                System.out.println("Update Post failure !");
                return "0";
            }
        }else{
            System.out.println("Post has ID "+ id + " not exits !");
            return "0";
        }

        return "1";
    }
    @RequestMapping(value = "/post-upload-update-no-video/{id}", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String updatePostNoVideo(
            @RequestParam("images") MultipartFile[] files,
            HttpServletRequest req,
            @PathVariable("id") Long id
    ) throws IOException {
        JSONObject data = new JSONObject(req.getParameter("info"));
        JSONObject isRemoveVideo = new JSONObject(req.getParameter("isRemoveVideo"));
        String checkExitsVideo = isRemoveVideo.getString("isRemoveVideo");

        BaiVietDao PostDao = new BaiVietDao();
        ChiTietBaiVietDao PostDetailDao = new ChiTietBaiVietDao();
        NguoiDungDao userDao = new NguoiDungDao();
        AnhDao imgDao = new AnhDao();

        List<BaiVietEntity> listPost = userDao.getPostByID(id.toString());
        BaiVietEntity targetPost = listPost.get(0);
        String imageIDs = data.getString("imageIDs").trim();
        String[] imageIDList = imageIDs.split("_");
        if(targetPost != null){
            targetPost.setTieude(data.getString("title"));
            targetPost.setDiachi(data.getString("street"));
            targetPost.setDientich((int)Float.parseFloat(data.getString("area")));
            targetPost.setTinhtrang(false);
            Double roundPrice  = (double) Math.round( Float.valueOf(data.getString("price")) * 10) / 10;
            targetPost.setGia(Float.valueOf(String.valueOf(roundPrice)));
            ChiTietBaiVietEntity targetPostDetail = targetPost.getChitietbaiviet();
            targetPostDetail.setMota(data.getString("description"));
            targetPostDetail.setPhuongxa(data.getString("wards"));
            targetPostDetail.setQuanhuyen(data.getString("district"));
            targetPostDetail.setTinhtp(data.getString("province"));
            if(Boolean.parseBoolean(checkExitsVideo)){
                targetPostDetail.setLinkVideo(null);
            }
            int updatePostResult = PostDao.UpdateBaiViet(targetPost);
            //  successfully update post will return 1
            if(updatePostResult == 1){
                int updatePostDetailResult = PostDetailDao.Update(targetPostDetail);
                System.out.println("Update Post Detail Result !" + updatePostDetailResult);
                Collection<AnhEntity> imageList = targetPost.getAnh();
                ArrayList <AnhEntity> imageListCV= new ArrayList<>(imageList);
                for(AnhEntity img:imageListCV){
                    if(!Arrays.toString(imageIDList).contains(img.getMaanh().toString())){
                        imgDao.Delete(img);
                    }
                }
                if(files.length > 0){
                    for(MultipartFile file: files){
                        AnhEntity a = new AnhEntity();
                        a.setLinkanh("Storage/Images/"+ writeFile(file,"Images"));
                        a.setBaiviet(targetPost);
                        imgDao.Insert(a);
                        System.out.println("Write image File");
                     }
                }

            }else{
                System.out.println("Update Post failure !");
                return "0";
            }
        }else{
            System.out.println("Post has ID "+ id + " not exits !");
            return "0";
        }

        return "1";
    }


    @RequestMapping(value = "/post-user-general", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getUserInfo(@RequestParam("avatar") MultipartFile imageFile,HttpServletRequest req) throws IOException {
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        NguoiDungEntity user = tk.getNguoidung();
        String path = "../Storage/Images/" + writeFile(imageFile,"Images");
        JSONObject data= new JSONObject(req.getParameter("userInfo"));
        String fullName = data.getString("fullName");
        String address = data.getString("address");
        String phoneNumber = data.getString("phoneNumber");
        String email = data.getString("email");
        user.setDiachi(address);
        user.setEmail(email);
        user.setSdt(phoneNumber);
        user.setLinkanhdaidien(path);
        user.setTenND(fullName);
        int result = userDao.updateUser(user);
        if(result == 1){
            return path;
        }else {
            return "0";
        }
    }

    @RequestMapping(value = "/post-feedback", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getFeedback(HttpServletRequest req) throws IOException {
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        NguoiDungEntity user = tk.getNguoidung();
        JSONObject data= new JSONObject(req.getParameter("feedback"));
        String feedback = data.getString("feedbackContent");
        String rating = data.getString("rating");
        System.out.println("Feedback " + feedback);
        System.out.println(rating);
        GopYEntity feedbackObj = new GopYEntity();
        feedbackObj.setNguoidung(user);
        feedbackObj.setNoidung(feedback);
        feedbackObj.setSosao(Integer.parseInt(rating));
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        feedbackObj.setThoigian(timestamp);
        GopYDao feedbackConnect = new GopYDao();
        int InsertFBResult = feedbackConnect.InsertGY(feedbackObj);
        if(InsertFBResult == 1){
            System.out.println("Insert Feedback successfully !");
            return "1";
        }
        return "0";
    }

    @RequestMapping(value = "/post-user-general-no-avatar", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public  String getUserInfoNoAvatar(HttpServletRequest req){
        JSONObject data= new JSONObject(req.getParameter("userInfo"));
        String fullName = data.getString("fullName");
        String address = data.getString("address");
        String phoneNumber = data.getString("phoneNumber");
        String email = data.getString("email");
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        NguoiDungEntity user = tk.getNguoidung();
        user.setDiachi(address);
        user.setEmail(email);
        user.setSdt(phoneNumber);
        user.setTenND(fullName);
        int result = userDao.updateUser(user);
        if(result == 1){
            return "1";
        }else {
            return "0";
        }
    }

    @RequestMapping(value = "/post-user-change-password", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getUserInfo(HttpServletRequest req) throws IOException {
        JSONObject data= new JSONObject(req.getParameter("password"));
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        TaiKhoanDao tkDao = new TaiKhoanDao();
        String currentPassword = tk.getMatkhau();

        String password = data.getString("password");
        String newPassword = data.getString("newPassword");

        System.out.println(password);
        System.out.println(newPassword);

            if(password.equals(newPassword)){
                return "-1";
            }else{
                if(passwordEncoder.matches(password,currentPassword))
                {
                    tk.setMatkhau(passwordEncoder.encode(newPassword));
                    tkDao.UpdateAccount(tk);
                    return "1";
                }else{
                    return "0";
                }
            }
    }


    @RequestMapping(value = "/user-signup", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String getAccountInfo(HttpServletRequest req) throws IOException {
        JSONObject data= new JSONObject(req.getParameter("account-info"));
        UserService se= new UserService();
        return  se.Signup(data,  passwordEncoder);
    }

    @GetMapping("/user-data")
    public User getUser(){
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        NguoiDungEntity currUser = tk.getNguoidung();
        User user = new User();
        user.setDiachi(currUser.getDiachi());
        user.setEmail(currUser.getEmail());
        user.setLinkanhdaidien(currUser.getLinkanhdaidien());
        user.setSdt(currUser.getSdt());
        user.setTenND(currUser.getTenND());
        return user;
    }

    @RequestMapping(value = "/post-hide-post", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String setHide(HttpServletRequest req) throws IOException {
        JSONObject data= new JSONObject(req.getParameter("id"));
        NguoiDungDao userDao = new NguoiDungDao();
        String username= userService.currentUserName();
        TaiKhoanEntity tk= userDao.findByUserName(username);
        BaiVietEntity post =new BaiVietEntity();
        String ID = data.getString("id");
        BaiVietDao bvD =new BaiVietDao();
        post = bvD.getById(Long.parseLong(ID)).get(0);
            if(tk.getNguoidung().getMaND()== post.getNguoidung().getMaND())
                if(bvD.SetAn(post))
                    return "1";
            return "0";
    }

    @RequestMapping(value = "/post-set-readed", method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public String setReaded(HttpServletRequest req) throws IOException {
        JSONObject data= new JSONObject(req.getParameter("id"));
        String notiID = data.getString("id");
        ThongBaoDao tbd = new ThongBaoDao();
        List <ThongBaoEntity> notiList = tbd.getTBFromID(notiID);
        if(notiList.size() > 0){
            ThongBaoEntity noti = notiList.get(0);
            noti.setDaDoc(true);
            int result = tbd.updateTB(noti);
            if(result==1){
                System.out.println("Set readed success!");
                return "1";
            }
        }
        return "0";
    }

    @RequestMapping("/trangchu")
    public String filtervalue(ModelMap model){
        model.addAttribute("province",province_global_variable);
        return "trangchu";
    }
    @RequestMapping(value = "/sendnoti",method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public  String addNoti(HttpServletRequest request) throws IOException{
        ThongBaoEntity tb =new ThongBaoEntity();
        ThongBaoDao tbDao = new ThongBaoDao();
       String id = request.getParameter("id");
       NguoiDungDao nguoiDungDao = new NguoiDungDao();
       String tiltle = request.getParameter("title");
       String content = request.getParameter("content");
        Date date = new Date();
        Timestamp tg = new Timestamp(date.getTime());
        tb.setNguoidung(nguoiDungDao.findById(Long.parseLong(id)));
        tb.setTieude(tiltle);
        tb.setNoidung(content);
        tb.setDaDoc(false);
        tb.setThoigian(tg);
        if(tbDao.InsertTB(tb)==1 )
            return "Thành công";
        else return "Thất bại";
    }
}


