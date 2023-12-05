package cn.evan.admin.user.application.convertor.menu;

import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import cn.evan.admin.user.sdk.feign.dto.MenuListVo;
import cn.evan.admin.user.sdk.feign.dto.Meta;
import cn.evan.zuo.common.entity.CommonMenuList;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MenuVO2DTO {
    List<UserMenuEntity> convertor(List<CommonMenuList> list);

    default List<MenuListVo> format(
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
                    MenuListVo menuListVo = new MenuListVo();
                    // meta
                    Meta meta = new Meta();
                    meta.setIcon(item.getIcon());
                    meta.setTitle(item.getTitle());
                    meta.setHideChildrenInMenu(item.getHideChildrenInMenu());
                    meta.setHideMenu(item.getHideMenu());
                    meta.setHideBreadcrumb(item.getHideBreadcrumb());
                    meta.setCurrentActiveMenu(item.getCurrentActiveMenu());
                    // 冗余字段
                    menuListVo.setIcon(item.getIcon());
                    menuListVo.setMeta(meta);
                    menuListVo.setParentMenu(item.getParentCid());
                    // 其他字段
                    menuListVo.setComponent(item.getComponent());
                    menuListVo.setPath(item.getPath());
                    menuListVo.setName(item.getName());
                    menuListVo.setRedirect(item.getRedirect());
                    menuListVo.setChildren(format(item.getChildren()));
                    menuListVo.setId(item.getCatId());
                    return menuListVo;
                }
        ).collect(Collectors.toList());
    }
    default List<MenuListVo> convertorVO2UO(List<UserMenuEntity> menuEntities) {
        List<MenuListVo> menuListVos = new ArrayList<>();
        menuEntities.forEach(userMenuEntity -> {
            MenuListVo menuListVo = new MenuListVo();
            Meta meta = new Meta();
            meta.setIcon(userMenuEntity.getIcon());
            meta.setTitle(userMenuEntity.getTitle());
            meta.setHideChildrenInMenu(userMenuEntity.getHideChildrenInMenu());
            meta.setHideMenu(userMenuEntity.getHideMenu());
            meta.setHideBreadcrumb(userMenuEntity.getHideBreadcrumb());
            meta.setCurrentActiveMenu(userMenuEntity.getCurrentActiveMenu());
            // 冗余字段
            menuListVo.setIcon(userMenuEntity.getIcon());
            menuListVo.setMeta(meta);
            menuListVo.setParentMenu(userMenuEntity.getParentCid());
            // 其他字段
            menuListVo.setComponent(userMenuEntity.getComponent());
            menuListVo.setPath(userMenuEntity.getPath());
            menuListVo.setName(userMenuEntity.getName());
            menuListVo.setRedirect(userMenuEntity.getRedirect());
            menuListVo.setChildren(format(userMenuEntity.getChildren()));
            menuListVo.setId(userMenuEntity.getCatId());
            menuListVos.add(menuListVo);
        });
        return menuListVos;
    };
}
