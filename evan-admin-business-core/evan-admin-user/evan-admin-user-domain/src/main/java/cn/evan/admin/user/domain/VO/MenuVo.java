package cn.evan.admin.user.domain.VO;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    private Integer total;
    private List<MenuListVo> list;
}
