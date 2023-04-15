package cn.gathub.demo.clients;

import cn.gathub.demo.config.Feignconfigs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "demo2", configuration = Feignconfigs.class)
public interface IAuthClient {
    @RequestMapping(value = "/demo2/user/introduce", consumes = "application/json", produces = "application/json", method = RequestMethod.POST)
    Object getUserIntroduce();
}
