package cn.evan.zuo.common.entity;

import lombok.Data;

@Data
public class EvanUserVo {
    String account;
    String createTime;
    String email;
    Long id;
    String nickname;
    String remark;
    String role;
    private int status;
}
