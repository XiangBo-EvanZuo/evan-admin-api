package cn.evanzuo.admin.business.user.sdk.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    private Integer total;
    private List<MenuListVo> list;
}
