package cn.evan.admin.pay.sdk.feign.dto;

import lombok.Data;

@Data
public class RoleListDTO {
    private Long id;
    private String roleName;
    private String roleValue;
    private Long status;
    private String remark;
    private String menu;
    private String urlList;
}
