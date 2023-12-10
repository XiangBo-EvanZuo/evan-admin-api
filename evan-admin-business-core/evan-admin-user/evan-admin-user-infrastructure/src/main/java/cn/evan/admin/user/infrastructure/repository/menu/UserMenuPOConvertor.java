package cn.evan.admin.user.infrastructure.repository.menu;

import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMenuPOConvertor {
    default List<UserMenuEntity> convertorPO2Entity(List<UserMenuPO> list) {
        List<UserMenuPO> projectMenus = list.stream()
                .filter(item -> item.getParentCid() == 0)
                .peek(item -> item.setChildren(getChildren(item, list)))
                .sorted(Comparator.comparingInt(UserMenuPO::getSort).reversed())
                .collect(Collectors.toList());
        List<UserMenuEntity> menus = new ArrayList<>();
        projectMenus.forEach(item -> {
            UserMenuEntity menu = new UserMenuEntity();
            menu.setCatId(item.getCatId());
            menu.setHideMenu(item.getHideMenu());
            menu.setComponent(item.getComponent());
            menu.setCurrentActiveMenu(item.getCurrentActiveMenu());
            menu.setCatLevel(item.getCatLevel());
            menu.setParentCid(item.getParentCid());
            menu.setHideBreadcrumb(item.getHideBreadcrumb());
            menu.setPath(item.getPath());
            menu.setRedirect(item.getRedirect());
            menu.setName(item.getName());
            menu.setTitle(item.getTitle());
            menu.setIcon(item.getIcon());
            menu.setShowStatus(item.getShowStatus());
            menu.setSort(item.getSort());
            menu.setHideChildrenInMenu(item.getHideChildrenInMenu());
            menu.setChildren(formatSubChild(item.getChildren()));
            menus.add(menu);
        });
        return menus;
    };

    default List<UserMenuEntity> formatSubChild(List<UserMenuPO> menus) {
        if (menus == null) {
            return new ArrayList<>();
        }
        if (menus.size() == 0) {
            return new ArrayList<>();
        }
        List<UserMenuEntity> userMenuEntityList = new ArrayList<>();
        menus.forEach(item -> {
            UserMenuEntity menu = new UserMenuEntity();
            menu.setCatId(item.getCatId());
            menu.setHideMenu(item.getHideMenu());
            menu.setComponent(item.getComponent());
            menu.setCurrentActiveMenu(item.getCurrentActiveMenu());
            menu.setCatLevel(item.getCatLevel());
            menu.setParentCid(item.getParentCid());
            menu.setHideBreadcrumb(item.getHideBreadcrumb());
            menu.setPath(item.getPath());
            menu.setRedirect(item.getRedirect());
            menu.setName(item.getName());
            menu.setTitle(item.getTitle());
            menu.setIcon(item.getIcon());
            menu.setShowStatus(item.getShowStatus());
            menu.setSort(item.getSort());
            menu.setHideChildrenInMenu(item.getHideChildrenInMenu());
            menu.setChildren(formatSubChild(item.getChildren()));
            userMenuEntityList.add(menu);
        });
        return userMenuEntityList;
    };

    default List<UserMenuPO> getChildren(UserMenuPO root, List<UserMenuPO> allMenus) {
        return allMenus.stream()
                .filter(item -> Objects.equals(item.getParentCid(), root.getCatId()))
                .peek(item -> item.setChildren(getChildren(item, allMenus)))
                .sorted(Comparator.comparingInt(UserMenuPO::getSort).reversed())
                .collect(Collectors.toList());
    }

}
