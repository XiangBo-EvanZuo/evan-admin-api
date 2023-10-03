package cn.evanzuo.admin.business.menu.VO;

import cn.evan.zuo.common.entity.CommonMenuList;
import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    private Integer total;
    private List<MenuListVo> list;
}
