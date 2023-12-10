package cn.evan.admin.pay.intf;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
/**
 * @author EvanZuo[739221432@qq.com] 2023/09/24
 */
@EnableFeignClients(basePackages = "cn.evan.admin.common.feign.client.clients")
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan({
        "cn.evan.admin.user.domain.mapper",
        "cn.evan.admin.user.infrastructure.repository"
})
@ComponentScan(basePackages = {
        "cn.evan.admin.user.domain", // 菜单service
        "cn.evan.admin.user.application", // 菜单service
        "cn.evan.admin.user.infrastructure", // 基础service
        "cn.evan.admin.user.intf", // 入口controller
        "cn.evan.admin.user.sdk",
})
public class BusinessUserApp {

  public static void main(String[] args) {
    SpringApplication.run(BusinessUserApp.class, args);
  }
}
