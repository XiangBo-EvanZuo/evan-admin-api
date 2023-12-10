package cn.evan.admin.order.domain.adaptor.feign;

import cn.evan.admin.user.sdk.feign.dto.MenuDTO;

public interface UserInfoFeignAdaptor {
    MenuDTO getMenuListFeign(String userStr);
}
