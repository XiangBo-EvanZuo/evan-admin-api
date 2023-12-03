package cn.evan.admin.user.domain.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class DeptListVo {
    private Long parentCid;
    private int sort;
    private Long catId;
    private String createTime;
    private String deptName;
    private String remark;
    private int status;
    @TableField(exist = false)
    private List<DeptListVo> children;
}
