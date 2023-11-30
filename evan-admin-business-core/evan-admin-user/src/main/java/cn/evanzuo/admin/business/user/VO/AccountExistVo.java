package cn.evanzuo.admin.business.user.VO;

import lombok.Data;

@Data
public class AccountExistVo {
    private Boolean exist;
    private String message;
}
