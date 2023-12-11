package cn.evan.admin.pay.intf;

import cn.evan.admin.pay.domain.mq.messaging.OrderSink;
import cn.evan.admin.pay.domain.mq.messaging.OrderSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@EnableFeignClients(basePackages = "cn.evan.admin.common.feign.client.clients")
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding({OrderSource.class, OrderSink.class})
@ComponentScan(basePackages = {
        "cn.evan.admin.user.sdk",
        "cn.evan.admin.pay",
})
public class BusinessPayApp {

  public static void main(String[] args) {
    SpringApplication.run(BusinessPayApp.class, args);
  }

}
