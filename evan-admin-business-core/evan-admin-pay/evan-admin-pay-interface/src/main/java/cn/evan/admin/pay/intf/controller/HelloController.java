package cn.evan.admin.pay.intf.controller;

import cn.evan.admin.common.feign.client.clients.EvanFeignUserInfo;
import cn.evan.admin.pay.intf.event.Order;
import cn.evan.admin.pay.intf.event.OrderCreateEvent;
import cn.evan.admin.pay.intf.mq.event.DelayCloseOrderEvent;
import cn.evan.admin.pay.intf.mq.provide.DelayCloseOrderProvide;
import cn.evan.admin.springboot.starter.ApplicationContextHolder;
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
  EvanFeignUserInfo evanFeignUserInfo;

  @Autowired
  DelayCloseOrderProvide provideOrderProvider;

  @GetMapping("/feign")
  public String feign(HttpServletRequest request) {

    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    String res = evanFeignUserInfo.getUserMenuList(userStr);
    log.info("/feign res:{}", res);
    return "Hello World ! feign";
  }

  @GetMapping("/mqProvider")
  public void mqProvider() {
    DelayCloseOrderEvent delayCloseOrderEvent = new DelayCloseOrderEvent();
    delayCloseOrderEvent.setOrderSn("123");
    provideOrderProvider.delayCloseOrderSend(delayCloseOrderEvent);
  }

  @GetMapping("/mqProviderEvent")
  public void mqProviderEvent() {
    Order order = Order.builder()
            .customerUserId(1L)
            .orderSn("orderSn")
            .build();
    // 观察者模式: 发布商品下单事件
    ApplicationContextHolder
            .getInstance()
            .publishEvent(new OrderCreateEvent(this, order));
  }
}

