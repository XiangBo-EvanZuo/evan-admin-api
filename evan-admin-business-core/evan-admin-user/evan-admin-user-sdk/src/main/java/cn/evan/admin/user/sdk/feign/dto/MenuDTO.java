package cn.evan.admin.user.sdk.feign.dto;

import lombok.Data;

import java.util.List;

@Data
public class MenuDTO {
    private Integer total;
    private List<MenuListDTO> list;
}
