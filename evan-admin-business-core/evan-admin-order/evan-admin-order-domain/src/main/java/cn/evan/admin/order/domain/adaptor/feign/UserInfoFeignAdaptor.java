package cn.evan.admin.order.domain.adaptor.feign;

import cn.evan.admin.user.sdk.feign.dto.MenuVo;

public interface UserInfoFeignAdaptor {
    MenuVo getMenuListFeign(String userStr);
}
