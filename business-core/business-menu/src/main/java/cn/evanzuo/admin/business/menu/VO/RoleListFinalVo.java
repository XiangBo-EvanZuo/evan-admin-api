package cn.evanzuo.admin.business.menu.VO;

import lombok.Data;

import java.util.List;

@Data
public class RoleListFinalVo {
    private Long id;
    private String roleName;
    private String roleValue;
    private Long status;
    private String remark;
    private List<String> menu;
}
