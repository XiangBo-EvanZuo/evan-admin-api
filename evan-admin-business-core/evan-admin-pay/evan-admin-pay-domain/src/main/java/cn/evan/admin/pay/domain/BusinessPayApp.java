package cn.evan.admin.pay.domain;

import cn.evan.admin.pay.domain.mq.messaging.OrderSink;
import cn.evan.admin.pay.domain.mq.messaging.OrderSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@EnableFeignClients(basePackages = "cn.evan.admin.common.feign.client.clients")
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding({OrderSource.class, OrderSink.class})
@MapperScan("cn.evan.admin.pay.domain.mapper")
public class BusinessPayApp {

  public static void main(String[] args) {
    SpringApplication.run(BusinessPayApp.class, args);
  }

}