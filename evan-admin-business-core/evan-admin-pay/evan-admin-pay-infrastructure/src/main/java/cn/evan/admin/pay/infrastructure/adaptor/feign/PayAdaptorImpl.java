package cn.evan.admin.pay.infrastructure.adaptor.feign;

import cn.evan.admin.common.feign.client.clients.EvanFeignUserInfo;
import cn.evan.admin.pay.domain.adaptor.pay.PayAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayAdaptorImpl implements PayAdaptor {
    @Autowired
    EvanFeignUserInfo evanFeignUserInfo;
    @Override
    public String feignGetUserInfo (String userStr) {
        return evanFeignUserInfo.getCurrentUser(userStr);
    }
}
