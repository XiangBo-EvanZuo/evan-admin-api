package cn.evanzuo.admin.business.user.sdk.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeptVo {
    private Integer total;
    private List<DeptListFormatVo> list;
}
