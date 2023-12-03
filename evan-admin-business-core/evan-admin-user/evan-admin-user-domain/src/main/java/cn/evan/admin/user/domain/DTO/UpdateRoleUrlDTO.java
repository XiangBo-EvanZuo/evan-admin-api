package cn.evan.admin.user.domain.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleUrlDTO {
    List<Integer>  list;
    Integer roleId;
}
