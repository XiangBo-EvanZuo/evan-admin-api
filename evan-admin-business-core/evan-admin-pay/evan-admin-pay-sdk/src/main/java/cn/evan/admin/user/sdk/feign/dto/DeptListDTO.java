package cn.evan.admin.user.sdk.feign.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

@Data
public class DeptListDTO {
    private Long parentCid;
    private int sort;
    private Long catId;
    private String createTime;
    private String deptName;
    private String remark;
    private int status;
    @TableField(exist = false)
    private List<DeptListDTO> children;
}
