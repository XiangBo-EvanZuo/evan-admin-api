package cn.evan.admin.user.application.service.user;

import cn.evan.admin.user.domain.DTO.PerCodeDTO;
import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import cn.evan.admin.user.domain.service.user.UserDomainService;
import cn.evan.admin.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@Service
public class UserApplication {
    @Autowired
    UserDomainService userAppService;
    public User currentUser(HttpServletRequest request) throws UnsupportedEncodingException {
        return userAppService.currentUser(request);
    }

    public PerCodeDTO getPermCode(HttpServletRequest request) throws UnsupportedEncodingException {
        return userAppService.getPermCode(request);
    }

    public List<UserMenuEntity> getMenuList(HttpServletRequest request) {
        return userAppService.getMenuList(request);
    }
}


