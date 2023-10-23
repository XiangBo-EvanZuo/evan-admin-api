package cn.evanzuo.admin.business.menu.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleUrlDTO {
    List<Integer>  list;
    Integer roleId;
}
