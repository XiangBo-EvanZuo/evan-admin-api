package cn.evan.admin.order.intf.controller;

import cn.evan.admin.order.application.service.feign.FeignApplication;
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
public class FeignDemoController {

  @Autowired
  FeignApplication feignApplication;
  @GetMapping("/feign")
  public String feign(HttpServletRequest request) {
    return feignApplication.feign(request);
  }
}

