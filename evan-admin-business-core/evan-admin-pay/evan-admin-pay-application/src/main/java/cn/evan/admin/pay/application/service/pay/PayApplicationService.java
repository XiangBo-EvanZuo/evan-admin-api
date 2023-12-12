package cn.evan.admin.pay.application.service.pay;
import cn.evan.admin.pay.domain.service.pay.PayDomainService;
import cn.evan.admin.user.sdk.feign.dto.CurrentUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Service
public class PayApplicationService {
    @Autowired
    PayDomainService payDomainService;

    public CurrentUserDTO feign(HttpServletRequest request) {
        return payDomainService.feign(request);
    }

    public String payDemo(HttpServletRequest request) {
        return payDomainService.payDemo(request);
    }

    public void mqProvider() {
        payDomainService.mqProvider();
    }

    public void mqProviderEvent() {
        payDomainService.mqProviderEvent();
    }

}


