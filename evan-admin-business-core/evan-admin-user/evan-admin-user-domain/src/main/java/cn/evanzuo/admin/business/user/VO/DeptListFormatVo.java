package cn.evanzuo.admin.business.user.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class DeptListFormatVo {
    private Long id;
    private String createTime;
    private String deptName;
    private String remark;
    private int status;
    private long parentDept;
    @TableField(exist = false)
    private List<DeptListFormatVo> children;
}
