package cn.evan.zuo.common.entity;

import lombok.Data;

@Data
public class EvanUser {
    private Long id;
    private String username;

    String mobile;
    Integer deleted;
    Integer notExistField;
    String avatarUrl;
    private boolean enabled = true;
    public boolean credentialsNonExpired = true;
    public boolean accountNonExpired = true;
    public boolean accountNonLocked = true;
}
