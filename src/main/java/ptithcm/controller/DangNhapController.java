package ptithcm.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ptithcm.dao.NguoiDungDao;
import ptithcm.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DangNhapController {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @RequestMapping("login")
    String login(){
        return "user/signin-page";
    }
    @RequestMapping("/403")
    String CanhBao(){
        return "user/page403";
    }
    @RequestMapping("/dangki")
    String  dangki() {
        return "user/signup-page";
    }
        @RequestMapping(value="/forgotpass",method=RequestMethod.GET)
        public String forgot(ModelMap model){
            return "user/forgotpass";
    }
    @RequestMapping(value="/checkmailforgot",method= RequestMethod.GET, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String forgot(HttpServletRequest req){
        String email = req.getParameter("email");
        UserService  userService =new UserService();
        String link = req.getRequestURL().toString().replace(req.getServletPath(), "")+ "/reset_password?token=";
       int ma= userService.SaveToken_SendMail(email,mailSender,link);
       if(ma==1) return "Đã gửi mail thành công";
       if(ma == 0) return "Địa chỉ mail chưa đăng kí tài khoản";
       return "Lỗi hệ thống, hãy thử lại sau";
    }
    @RequestMapping(value="/reset_password",method= RequestMethod.GET)
    public String  resetPass(){
        return "user/resetpass";
    }



    @RequestMapping(value="/reset_password",method = RequestMethod.POST, produces = "text/html;charset=UTF-8;multipart/form-data")
    @ResponseBody
    public Integer resetPass(HttpServletRequest req){
        JSONObject data = new JSONObject(req.getParameter("data"));
        String pass= data.getString("pass");
        String token = data.getString("token");
        UserService userService =new UserService();
        return userService.resetPass(token,pass,passwordEncoder);
    }
}

