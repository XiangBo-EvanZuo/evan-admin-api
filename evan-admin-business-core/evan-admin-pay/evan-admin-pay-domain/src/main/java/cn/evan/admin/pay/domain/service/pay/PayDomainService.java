package cn.evan.admin.pay.domain.service.pay;

import cn.evan.admin.common.feign.client.clients.EvanFeignUserInfo;
import cn.evan.admin.pay.domain.adaptor.pay.PayAdaptor;
import cn.evan.admin.pay.domain.event.Order;
import cn.evan.admin.pay.domain.event.OrderCreateEvent;
import cn.evan.admin.pay.domain.mq.event.DelayCloseOrderEvent;
import cn.evan.admin.pay.domain.mq.provide.DelayCloseOrderProvide;
import cn.evan.admin.springboot.starter.ApplicationContextHolder;
import cn.evan.admin.user.sdk.feign.dto.CurrentUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@Slf4j
@Service
public class PayDomainService {

  @Autowired
  PayAdaptor payAdaptor;

  @Autowired
  EvanFeignUserInfo evanFeignUserInfo;

  @Autowired
  DelayCloseOrderProvide provideOrderProvider;

  public CurrentUserDTO feign(HttpServletRequest request) {
    String userStr = request.getHeader("user");
    log.info("userStr feign: {}", userStr);
    return payAdaptor.feignGetUserInfo(userStr);
  }

  public String payDemo(HttpServletRequest request) {
    return "Hello World ! feign";
  }

  public void mqProvider() {
    DelayCloseOrderEvent delayCloseOrderEvent = new DelayCloseOrderEvent();
    delayCloseOrderEvent.setOrderSn("123");
    provideOrderProvider.delayCloseOrderSend(delayCloseOrderEvent);
  }

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

