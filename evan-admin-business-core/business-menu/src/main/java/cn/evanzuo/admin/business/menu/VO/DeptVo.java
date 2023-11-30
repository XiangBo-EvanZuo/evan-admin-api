package cn.evanzuo.admin.business.menu.VO;

import lombok.Data;

import java.util.List;

@Data
public class DeptVo {
    private Integer total;
    private List<DeptListFormatVo> list;
}
