package cn.evan.admin.user.sdk.feign.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class MenuListDTO {
    private Long id;
    private MetaDTO meta;
    private String icon;
    private Long parentMenu;
    private String path;
    private String name;
    private String component;
    private String redirect;
    @TableField(exist = false)
    private List<MenuListDTO> children;
}
