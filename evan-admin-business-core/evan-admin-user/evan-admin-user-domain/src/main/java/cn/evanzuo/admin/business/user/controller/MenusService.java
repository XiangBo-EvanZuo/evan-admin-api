package cn.evanzuo.admin.business.user.controller;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuListVo;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuVo;
import cn.evanzuo.admin.business.user.sdk.feign.dto.Meta;
import cn.evanzuo.admin.business.user.service.imp.ProjectMenuDBImpl;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenusService {
  @Autowired
  ProjectMenuDBImpl projectMenuDB;

  public MenuVo project(HttpServletRequest request) {
    // 从Header中获取用户信息
    String userStr = request.getHeader("user");
    JSONObject userJsonObject = new JSONObject(userStr);
    System.out.println((userJsonObject));

    List<String> authorities = (List<String>)userJsonObject.get("authorities");
    String authoritiesStr = authorities.stream()
            .map(item -> "`" + item + "`")
            .map(item -> item.replace("`", "'"))
            .collect(Collectors.joining(","));
    log.info(authorities.toString());
    log.info(authoritiesStr);
    System.out.println(authorities);
    List<CommonMenuList> allMenus = projectMenuDB.getBaseMapper().getMenuListByRole(authoritiesStr);
    MenuVo menuVo = new MenuVo();
    List<CommonMenuList> projectMenus = allMenus.stream()
            .filter(item -> item.getParentCid() == 0)
            .peek(item -> item.setChildren(MenusService.getChildren(item, allMenus)))
            .sorted(Comparator.comparingInt(CommonMenuList::getSort).reversed())
            .collect(Collectors.toList());

    menuVo.setList(format(projectMenus));
    log.info(projectMenus.toString());
    log.info(String.valueOf(projectMenus.size()));
    menuVo.setTotal(projectMenus.size());
    return menuVo;
  }

  public IPage<List<CommonMenuList>> menuListPage(HttpServletRequest request, Page page) {
      String userStr = request.getHeader("user");
      JSONObject userJsonObject = new JSONObject(userStr);
      System.out.println((userJsonObject));
      QueryWrapper queryWrapper = new QueryWrapper();
      IPage<List<CommonMenuList>> allMenus = projectMenuDB.page(page, queryWrapper);
      return allMenus;
  }
  public List<MenuListVo> format(
          List<CommonMenuList> commonMenuLists
  ) {
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

  public static List<CommonMenuList> getChildren(CommonMenuList root, List<CommonMenuList> allMenus) {
    return allMenus.stream()
            .filter(item -> Objects.equals(item.getParentCid(), root.getCatId()))
            .peek(item -> item.setChildren(MenusService.getChildren(item, allMenus)))
            .sorted(Comparator.comparingInt(CommonMenuList::getSort).reversed())
            .collect(Collectors.toList());
  }
}

