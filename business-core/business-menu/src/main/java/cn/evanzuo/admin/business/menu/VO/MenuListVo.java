package cn.evanzuo.admin.business.menu.VO;

import cn.evan.zuo.common.entity.CommonMenuList;
import lombok.Data;

import java.util.List;

@Data
public class MenuListVo {
    private Meta meta;
    private String path;
    private String name;
    private String component;
    private String redirect;

    private List<MenuListVo> children;
}
