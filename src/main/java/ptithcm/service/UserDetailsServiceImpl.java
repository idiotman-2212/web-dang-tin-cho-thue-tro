package ptithcm.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ptithcm.dao.NguoiDungDao;
import ptithcm.entity.TaiKhoanEntity;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDungDao tam= new NguoiDungDao();
        TaiKhoanEntity User= tam.findByUserName(username);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        String role = User.getQuyen().getTenquyen();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);
        UserDetails userDetails= new User(username,User.getMatkhau(),grantList);
        return userDetails;
    }
    public void register() {
    }
    
}
