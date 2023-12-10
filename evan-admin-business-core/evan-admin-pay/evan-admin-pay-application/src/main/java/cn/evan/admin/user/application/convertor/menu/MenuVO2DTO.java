package cn.evan.admin.user.application.convertor.menu;

import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import cn.evan.admin.user.sdk.feign.dto.MenuListDTO;
import cn.evan.admin.user.sdk.feign.dto.MetaDTO;
import cn.evan.zuo.common.entity.CommonMenuList;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MenuVO2DTO {
    List<UserMenuEntity> convertor(List<CommonMenuList> list);

    default List<MenuListDTO> format(
            List<UserMenuEntity> commonMenuLists
    ) {
        if (commonMenuLists == null) {
            return new ArrayList<>();
        }
        if (commonMenuLists.size() == 0) {
            return new ArrayList<>();
        }
        return commonMenuLists.stream().map(
                item -> {
                    MenuListDTO menuListDTO = new MenuListDTO();
                    // meta
                    MetaDTO metaDTO = new MetaDTO();
                    metaDTO.setIcon(item.getIcon());
                    metaDTO.setTitle(item.getTitle());
                    metaDTO.setHideChildrenInMenu(item.getHideChildrenInMenu());
                    metaDTO.setHideMenu(item.getHideMenu());
                    metaDTO.setHideBreadcrumb(item.getHideBreadcrumb());
                    metaDTO.setCurrentActiveMenu(item.getCurrentActiveMenu());
                    // 冗余字段
                    menuListDTO.setIcon(item.getIcon());
                    menuListDTO.setMeta(metaDTO);
                    menuListDTO.setParentMenu(item.getParentCid());
                    // 其他字段
                    menuListDTO.setComponent(item.getComponent());
                    menuListDTO.setPath(item.getPath());
                    menuListDTO.setName(item.getName());
                    menuListDTO.setRedirect(item.getRedirect());
                    menuListDTO.setChildren(format(item.getChildren()));
                    menuListDTO.setId(item.getCatId());
                    return menuListDTO;
                }
        ).collect(Collectors.toList());
    }
    default List<MenuListDTO> convertorVO2UO(List<UserMenuEntity> menuEntities) {
        List<MenuListDTO> menuListDTOS = new ArrayList<>();
        menuEntities.forEach(userMenuEntity -> {
            MenuListDTO menuListDTO = new MenuListDTO();
            MetaDTO metaDTO = new MetaDTO();
            metaDTO.setIcon(userMenuEntity.getIcon());
            metaDTO.setTitle(userMenuEntity.getTitle());
            metaDTO.setHideChildrenInMenu(userMenuEntity.getHideChildrenInMenu());
            metaDTO.setHideMenu(userMenuEntity.getHideMenu());
            metaDTO.setHideBreadcrumb(userMenuEntity.getHideBreadcrumb());
            metaDTO.setCurrentActiveMenu(userMenuEntity.getCurrentActiveMenu());
            // 冗余字段
            menuListDTO.setIcon(userMenuEntity.getIcon());
            menuListDTO.setMeta(metaDTO);
            menuListDTO.setParentMenu(userMenuEntity.getParentCid());
            // 其他字段
            menuListDTO.setComponent(userMenuEntity.getComponent());
            menuListDTO.setPath(userMenuEntity.getPath());
            menuListDTO.setName(userMenuEntity.getName());
            menuListDTO.setRedirect(userMenuEntity.getRedirect());
            menuListDTO.setChildren(format(userMenuEntity.getChildren()));
            menuListDTO.setId(userMenuEntity.getCatId());
            menuListDTOS.add(menuListDTO);
        });
        return menuListDTOS;
    };
}
