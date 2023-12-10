package cn.evan.admin.pay.sdk.feign.dto;

import lombok.Data;

@Data
public class AccountExistDTO {
    private Boolean exist;
    private String message;
}
