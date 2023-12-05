package cn.evan.admin.user.infrastructure.repository.menu;

import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMenuPOConvertor {
    List<UserMenuEntity> convertor(List<UserMenuPO> list);
}
