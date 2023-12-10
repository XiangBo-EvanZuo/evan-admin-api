package cn.evan.admin.order.domain.service;

import cn.evan.admin.order.domain.adaptor.feign.UserInfoFeignAdaptor;
import cn.evan.admin.user.sdk.feign.dto.MenuDTO;
import cn.evan.admin.common.feign.client.clients.EvanFeignUserInfo;
import cn.evan.admin.common.feign.client.clients.EvanFeignPayInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Slf4j
@Service
public class FeignDemoDomainService {
  @Autowired
  EvanFeignUserInfo evanFeignUserInfo;

  @Autowired
  EvanFeignPayInfo evanFeignPayInfo;

  @Autowired
  UserInfoFeignAdaptor userInfoFeignAdaptor;

  public String feign(HttpServletRequest request) {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    MenuDTO menuDTO = userInfoFeignAdaptor.getMenuListFeign(userStr);
    log.info("menuVo: {}", menuDTO);
    // todo: 封装到user sdk
    String currentUserInfo = evanFeignUserInfo.getCurrentUser(userStr);
    log.info("currentUserInfo res:{}", currentUserInfo);
    // todo: 封装到pay sdk
    String payInfo = evanFeignPayInfo.getUserIntroduce(userStr);
    log.info("payInfo res:{}", payInfo);
    return "Hello World ! feign! 3个Feign连续调用";
  }
}

