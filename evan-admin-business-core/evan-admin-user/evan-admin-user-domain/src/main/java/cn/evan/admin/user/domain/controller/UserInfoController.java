package cn.evan.admin.user.domain.controller;


import cn.evan.admin.user.domain.DTO.PerCodeDTO;
import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import cn.evan.admin.user.domain.entity.RoleItem;
import cn.evan.admin.user.domain.entity.User;
import cn.evan.admin.user.domain.service.menu.MenusService;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
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
  MenusService menusService;

  @GetMapping("/currentUser")
  public User currentUser(HttpServletRequest request) throws UnsupportedEncodingException {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    log.info("userStr: {}", userStr);
    JSONObject userJsonObject = new JSONObject(userStr);
    String roles = (String) userJsonObject.get("roles");
    List<RoleItem> roleObj = (List<RoleItem>) JSON.parse(URLDecoder.decode(roles, "UTF-8"));
    return User.builder()
            .username(userJsonObject.getStr("user_name"))
            .nickName(userJsonObject.getStr("nickName"))
            .id(Convert.toLong(userJsonObject.get("id")))
            .roles(roleObj)
            .build();
  }

  // todo: RBAC0模型完善
  @GetMapping("/getPermCode")
  public PerCodeDTO getPermCode(HttpServletRequest request) throws UnsupportedEncodingException {
    return PerCodeDTO.builder()
            .perCodeList(Collections.singletonList("1000, 4000, 5000"))
            .build();
  }

  @GetMapping("/getMenuList")
  public List<UserMenuEntity> getMenuList(HttpServletRequest request) {
      return menusService.project(request);
  }

  @GetMapping
  public JSONObject findUser(HttpServletRequest request) {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    return new JSONObject(userStr);
  }
}
