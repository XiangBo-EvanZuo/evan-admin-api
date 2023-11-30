package cn.evanzuo.admin.business.user.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class MenuListVo {
    private Long id;
    private Meta meta;
    private String icon;
    private Long parentMenu;
    private String path;
    private String name;
    private String component;
    private String redirect;
    @TableField(exist = false)
    private List<MenuListVo> children;
}
