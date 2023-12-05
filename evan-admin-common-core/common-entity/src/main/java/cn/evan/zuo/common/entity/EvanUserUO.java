package cn.evan.zuo.common.entity;

import lombok.Data;

import java.util.List;

@Data
public class EvanUserUO {
    String account;
    String createTime;
    String email;
    Long id;
    String nickname;
    String remark;
    List<String> role;
    private int status;
}
