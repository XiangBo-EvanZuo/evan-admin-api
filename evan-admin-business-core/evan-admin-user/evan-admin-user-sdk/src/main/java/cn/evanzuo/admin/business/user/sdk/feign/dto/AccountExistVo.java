package cn.evanzuo.admin.business.user.sdk.feign.dto;

import lombok.Data;

@Data
public class AccountExistVo {
    private Boolean exist;
    private String message;
}
