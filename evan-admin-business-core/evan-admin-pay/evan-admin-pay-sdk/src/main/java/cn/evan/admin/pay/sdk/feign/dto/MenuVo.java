package cn.evan.admin.pay.sdk.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuVo {
    private Integer total;
    private List<MenuListDTO> list;
}
