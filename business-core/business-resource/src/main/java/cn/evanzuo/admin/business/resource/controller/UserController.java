package cn.evanzuo.admin.business.resource.controller;


import cn.evan.zuo.common.entity.CommonApiResult;
import cn.evanzuo.admin.business.resource.DTO.PerCodeDTO;
import cn.evanzuo.admin.business.resource.api.CommonResult;
import cn.evanzuo.admin.business.resource.domain.MenuList;
import cn.evanzuo.admin.business.resource.domain.RoleItem;
import cn.evanzuo.admin.common.feign.client.clients.FeignGetMenuList;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import cn.evanzuo.admin.business.resource.domain.User;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 获取登录用户信息接口
 *
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@RestController
@RequestMapping("/user")
public class UserController {
  private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Autowired
  FeignGetMenuList feignGetMenuList;

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
            .realName("123")
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

  @GetMapping("/feignGetMenuList")
  public Object feignGetMenuList(HttpServletRequest request) throws UnsupportedEncodingException {
    String userStr = request.getHeader("user");
    String menuListStr = feignGetMenuList.getUserIntroduce(userStr);
    LOGGER.info("menuListStr: {}", menuListStr);
    CommonApiResult demo = JSON.parseObject(menuListStr, CommonApiResult.class);
    return demo.getData();
  }

  @GetMapping
  public JSONObject findUser(HttpServletRequest request) {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    return new JSONObject(userStr);
  }
}
