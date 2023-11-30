package cn.evanzuo.admin.business.demo.controller;

import cn.evanzuo.admin.business.demo.domain.User;
import cn.evanzuo.admin.business.demo.service.imp.UserServiceDBImpl;
import cn.evanzuo.admin.common.feign.client.clients.FeignGetMenuList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@RestController
public class HelloController {
  private final static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
  @Autowired
  FeignGetMenuList iDemo3Client;

  @Resource
  private UserServiceDBImpl userServiceDB;

  @GetMapping("/hello")
  public String hello() {
    return "Hello World !";
  }

  @GetMapping("/demo")
  public String demo() {
    return "Hello World ! demo";
  }

  @GetMapping("/feign")
  public String feign(HttpServletRequest request) {

    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    String res = iDemo3Client.getUserIntroduce(userStr);
    LOGGER.info("/feign res:{}", res);
    return "Hello World ! feign";
  }
  @GetMapping("/userList")
  public List<User> userList() {
    return userServiceDB.list();
  }
}

