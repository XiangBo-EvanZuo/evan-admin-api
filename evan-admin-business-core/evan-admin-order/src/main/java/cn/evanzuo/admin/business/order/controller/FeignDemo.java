package cn.evanzuo.admin.business.order.controller;

import cn.evanzuo.admin.common.feign.client.clients.EvanFeignUserInfo;
import cn.evanzuo.admin.common.feign.client.clients.EvanFeignPayInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@RestController
public class FeignDemo {
  private final static Logger LOGGER = LoggerFactory.getLogger(FeignDemo.class);
  @Resource
  EvanFeignUserInfo evanFeignUserInfo;

  @Resource
  EvanFeignPayInfo evanFeignPayInfo;

  @GetMapping("/feign")
  public String feign(HttpServletRequest request) {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    String menuList = evanFeignUserInfo.getUserMenuList(userStr);
    LOGGER.info("menuList res:{}", menuList);
    String payInfo = evanFeignPayInfo.getUserIntroduce(userStr);
    LOGGER.info("payInfo res:{}", payInfo);
    String currentUserInfo = evanFeignUserInfo.getCurrentUser(userStr);
    LOGGER.info("currentUserInfo res:{}", currentUserInfo);
    return "Hello World ! feign! 3个Feign连续调用";
  }
}

