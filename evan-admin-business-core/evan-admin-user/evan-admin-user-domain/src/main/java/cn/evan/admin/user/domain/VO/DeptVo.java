package cn.evan.admin.user.domain.VO;

import lombok.Data;

import java.util.List;

@Data
public class DeptVo {
    private Integer total;
    private List<DeptListFormatVo> list;
}
