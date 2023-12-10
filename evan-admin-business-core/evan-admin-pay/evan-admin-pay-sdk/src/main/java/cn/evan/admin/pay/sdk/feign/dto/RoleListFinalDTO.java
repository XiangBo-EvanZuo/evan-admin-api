package cn.evan.admin.pay.sdk.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleListFinalDTO {
    private Long id;
    private String roleName;
    private String roleValue;
    private Long status;
    private String remark;
    private List<Integer> menu;
    private List<Integer> urlList;
}
