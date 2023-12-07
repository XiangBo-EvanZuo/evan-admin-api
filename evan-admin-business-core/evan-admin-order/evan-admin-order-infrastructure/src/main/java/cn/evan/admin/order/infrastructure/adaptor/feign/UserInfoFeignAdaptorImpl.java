package cn.evan.admin.order.infrastructure.adaptor.feign;

import cn.evan.admin.order.domain.adaptor.feign.UserInfoFeignAdaptor;
import cn.evan.admin.user.sdk.feign.api.impl.UserFeignProviderImpl;
import cn.evan.admin.user.sdk.feign.dto.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoFeignAdaptorImpl implements UserInfoFeignAdaptor {
    @Autowired
    UserFeignProviderImpl userFeignProvider;
    @Override
    public MenuVo getMenuListFeign(String userStr) {
        return userFeignProvider.menuListFeign(userStr);
    }
}
