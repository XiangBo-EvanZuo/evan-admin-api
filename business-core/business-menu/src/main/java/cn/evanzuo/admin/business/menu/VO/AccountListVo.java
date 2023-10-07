package cn.evanzuo.admin.business.menu.VO;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AccountListVo {
    String account;
    String createTime;
    String email;
    String id;
    String nickname;
    String remark;
    String role;
   private int status;
}
