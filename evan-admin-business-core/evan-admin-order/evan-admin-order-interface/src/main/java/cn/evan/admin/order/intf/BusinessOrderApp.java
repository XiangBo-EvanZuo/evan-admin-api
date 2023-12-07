package cn.evan.admin.order.intf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@EnableFeignClients(basePackages = {
        "cn.evan.admin.common.feign.client.clients",
})
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.evan.admin.order.domain.mapper")
@ComponentScan(basePackages = {
        "cn.evan.admin.order",
        "cn.evan.admin.user.sdk",
        "cn.evan.admin.order.infrastructure"
})
public class BusinessOrderApp {

  public static void main(String[] args) {
    SpringApplication.run(BusinessOrderApp.class, args);
  }

}
