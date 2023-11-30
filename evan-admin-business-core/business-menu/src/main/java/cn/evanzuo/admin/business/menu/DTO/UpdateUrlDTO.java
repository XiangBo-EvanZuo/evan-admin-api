package cn.evanzuo.admin.business.menu.DTO;

import lombok.Data;

@Data
public class UpdateUrlDTO {
    private Integer id;
    private Integer moduleId;
    private String path;
}
