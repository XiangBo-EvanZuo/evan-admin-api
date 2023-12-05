package cn.evan.admin.user.sdk.feign.dto;

import lombok.Data;

@Data
public class AccountExistDTO {
    private Boolean exist;
    private String message;
}
