package cn.evan.admin.pay.infrastructure.adaptor.feign;

import cn.evan.admin.common.feign.client.clients.EvanFeignUserInfo;
import cn.evan.admin.pay.domain.adaptor.pay.PayAdaptor;
import cn.evan.admin.user.sdk.feign.api.impl.UserFeignProviderImpl;
import cn.evan.admin.user.sdk.feign.dto.CurrentUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayAdaptorImpl implements PayAdaptor {
    @Autowired
    UserFeignProviderImpl userFeignProvider;
    @Override
    public CurrentUserDTO feignGetUserInfo (String userStr) {
        return userFeignProvider.getCurrentUser(userStr);
    }
}
