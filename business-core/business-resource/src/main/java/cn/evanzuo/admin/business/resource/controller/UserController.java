package cn.evanzuo.admin.business.resource.controller;


import cn.evanzuo.admin.business.resource.domain.RoleItem;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import cn.evanzuo.admin.business.resource.domain.User;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 获取登录用户信息接口
 *
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping("/currentUser")
  public User currentUser(HttpServletRequest request) throws UnsupportedEncodingException {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    System.out.println("----");
    System.out.println(userStr);
    JSONObject userJsonObject = new JSONObject(userStr);
    String roles = (String) userJsonObject.get("roles");
    List<RoleItem> roleObj = (List<RoleItem>) JSON.parse(URLDecoder.decode(roles, "UTF-8"));
    return User.builder()
        .username(userJsonObject.getStr("user_name"))
        .id(Convert.toLong(userJsonObject.get("id")))
        .roles(Convert.toList(String.class, userJsonObject.get("authorities")))
            .rolesItem(roleObj)
            .build();
  }

  @GetMapping
  public JSONObject findUser(HttpServletRequest request) {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    return new JSONObject(userStr);
  }
}
