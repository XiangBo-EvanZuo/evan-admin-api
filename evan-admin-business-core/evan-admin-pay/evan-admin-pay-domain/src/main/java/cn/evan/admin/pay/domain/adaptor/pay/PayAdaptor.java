package cn.evan.admin.pay.domain.adaptor.pay;

import cn.evan.admin.user.sdk.feign.dto.CurrentUserDTO;

public interface PayAdaptor {
    CurrentUserDTO feignGetUserInfo(String userStr);
}