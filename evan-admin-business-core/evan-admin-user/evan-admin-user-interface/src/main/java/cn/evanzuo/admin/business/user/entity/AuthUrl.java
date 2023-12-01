package cn.evanzuo.admin.business.user.entity;

import lombok.Data;

@Data
public class AuthUrl {
    private Long id;
    private String url;
    private Long status;
    private String moduleName;
    private String path;
    private Long moduleId;
}
