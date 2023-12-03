package cn.evan.admin.common.feign.client.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "evan-admin-user")
public interface EvanFeignUserInfo {
    @RequestMapping(
            value = "/user/currentUser",
            method = RequestMethod.GET
    )
    // user 放到Rquest的header里面，其他微服务可以从Request里面取到
    String getCurrentUser(@RequestHeader("user") String headers);

    @RequestMapping(
            value = "/menu/list",
            method = RequestMethod.POST
    )
    String getUserMenuList(@RequestHeader("user") String headers);
}
