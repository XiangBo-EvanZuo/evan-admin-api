package cn.evanzuo.admin.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("cn.evanzuo.admin.gateway.mapper")
public class EvanAdminGateway {

  public static void main(String[] args) {
    SpringApplication.run(EvanAdminGateway.class, args);
  }

}
