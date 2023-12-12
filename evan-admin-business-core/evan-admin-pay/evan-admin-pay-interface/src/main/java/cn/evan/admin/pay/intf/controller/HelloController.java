package cn.evan.admin.pay.intf.controller;

import cn.evan.admin.pay.application.service.pay.PayApplicationService;
import cn.evan.admin.user.sdk.feign.dto.CurrentUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Slf4j
@RestController
public class HelloController {

  @Autowired
  PayApplicationService payApplicationService;
  @GetMapping("/feign")
  public CurrentUserDTO feign(HttpServletRequest request) {
    return payApplicationService.feign(request);
  }

  @GetMapping("/pay-demo")
  public String payDemo(HttpServletRequest request) {
    return payApplicationService.payDemo(request);
  }

  @GetMapping("/mqProvider")
  public void mqProvider() {
    payApplicationService.mqProvider();
  }

  @GetMapping("/mqProviderEvent")
  public void mqProviderEvent() {
    payApplicationService.mqProviderEvent();
  }
}

