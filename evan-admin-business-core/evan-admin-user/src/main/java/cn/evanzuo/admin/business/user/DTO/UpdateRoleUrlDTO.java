package cn.evanzuo.admin.business.user.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleUrlDTO {
    List<Integer>  list;
    Integer roleId;
}
