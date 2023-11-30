package cn.evanzuo.admin.business.order.controller;

import cn.evanzuo.admin.common.feign.client.clients.EvanFeignUserInfo;
import cn.evanzuo.admin.common.feign.client.clients.FeignGetMenuList;
import cn.evanzuo.admin.common.feign.client.clients.IDemo4Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@RestController
public class HelloController {
  private final static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
  @Autowired
  FeignGetMenuList iDemo3Client;

  @Autowired
  IDemo4Client iDemo4Client;

  @Autowired
  EvanFeignUserInfo evanFeignUserInfo;


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
    String resDemo3 = iDemo3Client.getUserIntroduce(userStr);
    LOGGER.info("/feignDemo3 res:{}", resDemo3);
    String resDemo4 = iDemo4Client.getUserIntroduce(userStr);
    LOGGER.info("/feignDemo4 res:{}", resDemo4);
    String userInfo = evanFeignUserInfo.getUserIntroduce(userStr);
    LOGGER.info("/evanFeignUserInfo res:{}", userInfo);
    return "Hello World ! feign";
  }
}

