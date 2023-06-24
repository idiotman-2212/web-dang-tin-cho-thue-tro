package ptithcm.service.Interface;

import org.springframework.security.core.Authentication;

public interface IUserService {
    Authentication getAuthentication();
    String currentUserName();
}
