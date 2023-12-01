package cn.evanzuo.admin.business.user.sdk.feign.dto;
import lombok.Data;

@Data
public class Meta {
    private Boolean hideChildrenInMenu;
    private String icon;
    private String title;
    private Boolean hideMenu;
    private Boolean hideBreadcrumb;
    private String currentActiveMenu;

}