package cn.evan.admin.user.domain.VO;

import lombok.Data;

@Data
public class AccountExistVo {
    private Boolean exist;
    private String message;
}
