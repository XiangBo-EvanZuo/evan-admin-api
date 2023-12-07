package cn.evan.admin.order.application.service.feign;

import cn.evan.admin.order.domain.service.FeignDemoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class FeignApplication {
    @Autowired
    FeignDemoDomainService feignDemoDomainService;
    public String feign(HttpServletRequest request) {
    	return feignDemoDomainService.feign(request);
    }
}


