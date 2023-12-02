package cn.evanzuo.admin.business.order.controller;

//import cn.evanzuo.admin.business.user.sdk.feign.api.impl.UserFeignProviderImpl;
import cn.evanzuo.admin.business.user.sdk.feign.api.impl.UserFeignProviderImpl;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuVo;
import cn.evanzuo.admin.common.feign.client.clients.EvanFeignUserInfo;
import cn.evanzuo.admin.common.feign.client.clients.EvanFeignPayInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Slf4j
@RestController
public class FeignDemo {
  @Resource
  EvanFeignUserInfo evanFeignUserInfo;

  @Autowired
  EvanFeignPayInfo evanFeignPayInfo;

  @Autowired
  UserFeignProviderImpl userFeignProvider;

  @GetMapping("/feign")
  public String feign(HttpServletRequest request) {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    MenuVo menuVo = userFeignProvider.menuListFeign(userStr);
    log.info("menuVo: {}", menuVo);
    String menuList = evanFeignUserInfo.getUserMenuList(userStr);
    log.info("menuList res:{}", menuList);
    String payInfo = evanFeignPayInfo.getUserIntroduce(userStr);
    log.info("payInfo res:{}", payInfo);
    String currentUserInfo = evanFeignUserInfo.getCurrentUser(userStr);
    log.info("currentUserInfo res:{}", currentUserInfo);
    return "Hello World ! feign! 3个Feign连续调用";
  }
}

