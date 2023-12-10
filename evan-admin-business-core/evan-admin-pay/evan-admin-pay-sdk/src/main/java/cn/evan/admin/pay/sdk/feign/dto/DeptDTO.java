package cn.evan.admin.pay.sdk.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeptDTO {
    private Integer total;
    private List<DeptListFormatDTO> list;
}
