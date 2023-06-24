package ptithcm.service;

import net.bytebuddy.utility.RandomString;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ptithcm.dao.NguoiDungDao;
import ptithcm.dao.PasswordResetTokenDAO;
import ptithcm.dao.TaiKhoanDao;
import ptithcm.entity.NguoiDungEntity;
import ptithcm.entity.PasswordResetTokenEntity;
import ptithcm.entity.TaiKhoanEntity;
import ptithcm.hibernate.HibernateUtil;
import ptithcm.service.Interface.IUserService;

import javax.mail.internet.MimeMessage;
import java.util.Calendar;

@Service
public class UserService implements IUserService {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    // Lấy user name đã đăng đăng nhập
    public String currentUserName() {
        Authentication authentication = this.getAuthentication();
        return authentication.getName();
    }
    public String Signup( JSONObject data,BCryptPasswordEncoder passwordEncoder)
    {
        String fullName = data.getString("fullname");
        String password = data.getString("password");
        String email = data.getString("email");
        String phoneNumber = data.getString("phoneNumber");
        String username = data.getString("username");
        String address = data.getString("address");
        NguoiDungDao nguoiDungDao =new NguoiDungDao();
        TaiKhoanEntity tk =new TaiKhoanEntity();
        NguoiDungEntity nguoiDung =new NguoiDungEntity();
        nguoiDung.setDiachi(address);
        nguoiDung.setEmail(email);
        nguoiDung.setTenND(fullName);
        nguoiDung.setSdt(phoneNumber);
        nguoiDung.setLinkanhdaidien("../Storage/Images/default_avatar.jpg");
        tk.setTenDN(username);
        tk.setMatkhau(passwordEncoder.encode(password));
        int checkES=nguoiDungDao.checkEmailSdt(email,phoneNumber);
        if(checkES==2) {
            return "Số điện thoại hoặc email đã được sử dụng";
        }
        if(checkES==0)
        return "Có lỗi xảy ra, hãy thử lại sau";
         int checkNgd= nguoiDungDao.insertTk(tk);
        if(checkNgd==2){
            return "Tên đăng nhập đã bị trùng";
        }
        if(checkNgd==0){

        }
        nguoiDung.setTaikhoan(this.find(username));
        if(nguoiDungDao.insert(nguoiDung)==0)
            return "Có lỗi xảy ra, hãy thử lại sau";
        return "Thành công";
    }
    public UserService(){
        super();
    }
    public TaiKhoanEntity find(String name){
        TaiKhoanEntity tk= new TaiKhoanEntity();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql="from TaiKhoanEntity AS tk where  tk.tenDN = :name";
            tk = session.createQuery(hql,TaiKhoanEntity.class).setParameter("name", name).uniqueResult();
            return tk;
        } catch(Exception e){
            return null;
        }
    }
    public String randomToken(){
        return RandomString.make(30);
    }
    public Integer SaveToken_SendMail(String email, JavaMailSender mailSender, String link){
        String token =this.randomToken();
        NguoiDungDao nguoiDungDao =new NguoiDungDao();
        NguoiDungEntity nguoiDungEntity=nguoiDungDao.findUserByEmail(email);
        if(nguoiDungEntity==null) {
            return 0; //Không tìm thấy người dùng
        }
        else{
            PasswordResetTokenEntity a = new PasswordResetTokenEntity(token,nguoiDungEntity.getMaND());
            a.setExpireDate();
            PasswordResetTokenDAO passwordResetTokenDAO =new PasswordResetTokenDAO();
            if(passwordResetTokenDAO.Insert(a)){
                try {
                    MimeMessage mail= mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(mail);
                    helper.setTo(email);
                    helper.setSubject("Quên mật khẩu");
                    String content= "Xin chào" +
                            "<p>Vui lòng click vào link bên dưới để đổi mật khẩu mới:</p>" +
                            "<p>Ấn để đổi mật khẩu <a href='" + link+ token+
                            "'></a></p>" +
                            "<br>"
                            ;
                    helper.setText(content,true);
                    mailSender.send(mail);
                }
                catch(Exception e) {
                   return -2; //Lỗi gửi email
                }
            }
            else {
                return -1; //Lỗi lưu token
            }
            return 1; //Thành công
        }

    }
    public Integer resetPass(String token, String pass, PasswordEncoder passwordEncoder){
        PasswordResetTokenDAO passwordResetTokenDAO =new PasswordResetTokenDAO();
        passwordResetTokenDAO.FindByToken(token);
        PasswordResetTokenEntity passwordResetToken =passwordResetTokenDAO.FindByToken(token);
        if(passwordResetToken== null){
            return 0; //token bị sai
        }else {
            final Calendar cal = Calendar.getInstance();
            if ((passwordResetToken.getExpireDate().getTime() - cal.getTime().getTime()) >= 0) {
                TaiKhoanDao taiKhoanDao = new TaiKhoanDao();
                TaiKhoanEntity nguoiDung = passwordResetToken.getNguoidung().getTaikhoan();
                nguoiDung.setMatkhau(passwordEncoder.encode(pass));
                if(taiKhoanDao.UpdateAccount(nguoiDung)==1)
                {
                    return 1; //đổi thành công
                }
                else return 0; // đổi bị lỗi
              }
            else
            {
                return -1; // Token hết hạn
            }
            }
    }
}
