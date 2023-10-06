package cn.evanzuo.admin.business.menu.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DeptListVo {
    private Long parentCid;
    private int sort;
    private Long catId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;
    private String deptName;
    private String remark;
    private int status;

    private List<DeptListVo> children;
}
