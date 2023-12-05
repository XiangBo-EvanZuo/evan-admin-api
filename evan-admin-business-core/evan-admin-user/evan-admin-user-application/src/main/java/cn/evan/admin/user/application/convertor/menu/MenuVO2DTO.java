package cn.evan.admin.user.application.convertor.menu;

import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import cn.evan.zuo.common.entity.CommonMenuList;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuVO2DTO {
    List<UserMenuEntity> convertor(List<CommonMenuList> list);
}
