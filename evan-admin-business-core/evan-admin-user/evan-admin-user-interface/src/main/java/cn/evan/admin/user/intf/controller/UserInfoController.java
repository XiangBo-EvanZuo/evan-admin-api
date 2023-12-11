package cn.evan.admin.user.intf.controller;


import cn.evan.admin.user.application.service.user.UserApplication;
import cn.evan.admin.user.domain.DTO.PerCodeDTO;
import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import cn.evan.admin.user.domain.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 获取登录用户信息接口
 *
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {
  @Autowired
  UserApplication userAppService;

  @GetMapping("/currentUser")
  public User currentUser(HttpServletRequest request) throws UnsupportedEncodingException {
     return userAppService.currentUser(request);
  }

  // todo: RBAC0模型完善
  @GetMapping("/getPermCode")
  public PerCodeDTO getPermCode(HttpServletRequest request) throws UnsupportedEncodingException {
    return userAppService.getPermCode(request);
  }

  @GetMapping("/getMenuList")
  public List<UserMenuEntity> getMenuList(HttpServletRequest request) {
    return userAppService.getMenuList(request);
  }
}
