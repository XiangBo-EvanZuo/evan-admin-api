package cn.evanzuo.admin.business.menu.VO;

import cn.evan.zuo.common.entity.CommonMenuList;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class MenuListVo {
    private String id;
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
