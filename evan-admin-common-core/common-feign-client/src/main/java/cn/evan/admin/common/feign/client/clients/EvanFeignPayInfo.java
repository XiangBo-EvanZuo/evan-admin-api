package cn.evan.admin.common.feign.client.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "evan-admin-pay")
public interface EvanFeignPayInfo {
    @RequestMapping(
            value = "/pay-demo",
            method = RequestMethod.GET
    )
    String getUserIntroduce(@RequestHeader("user") String headers);
}
