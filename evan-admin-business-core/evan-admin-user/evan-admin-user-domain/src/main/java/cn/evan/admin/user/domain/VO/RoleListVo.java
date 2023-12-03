package cn.evan.admin.user.domain.VO;

import lombok.Data;

@Data
public class RoleListVo {
    private Long id;
    private String roleName;
    private String roleValue;
    private Long status;
    private String remark;
    private String menu;
    private String urlList;
}
