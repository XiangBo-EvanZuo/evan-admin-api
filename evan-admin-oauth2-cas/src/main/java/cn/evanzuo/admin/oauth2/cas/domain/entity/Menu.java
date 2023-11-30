package cn.evanzuo.admin.oauth2.cas.domain.entity;

import lombok.Data;

@Data
public class Menu {
    Integer id;
    String path;
    Integer parentId;
    String parentName;
    String title;
    boolean deleted;
    Integer order;
    Integer subHideMenuId;
}
