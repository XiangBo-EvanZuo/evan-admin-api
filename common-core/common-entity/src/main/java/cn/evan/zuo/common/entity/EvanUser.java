package cn.evan.zuo.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class EvanUser extends EvanUserExtra {
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

    private String roles;
}
