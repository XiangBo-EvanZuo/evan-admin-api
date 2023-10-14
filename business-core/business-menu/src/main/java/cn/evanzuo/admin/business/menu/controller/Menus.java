package cn.evanzuo.admin.business.menu.controller;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.menu.VO.MenuListVo;
import cn.evanzuo.admin.business.menu.VO.MenuVo;
import cn.evanzuo.admin.business.menu.VO.Meta;
import cn.evanzuo.admin.business.menu.service.imp.ProjectMenuDBImpl;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/menu")
public class Menus {
  private final static Logger LOGGER = LoggerFactory.getLogger(Menus.class);

  @Autowired
  ProjectMenuDBImpl projectMenuDB;

  @PostMapping("/list")
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
    LOGGER.info(authorities.toString());
    LOGGER.info(authoritiesStr);
    System.out.println(authorities);
    List<CommonMenuList> allMenus = projectMenuDB.getBaseMapper().getRoleNames(authoritiesStr);
    MenuVo menuVo = new MenuVo();
    List<CommonMenuList> projectMenus = allMenus.stream()
            .filter(item -> item.getParentCid() == 0)
            .peek(item -> item.setChildren(Menus.getChildren(item, allMenus)))
            .sorted(Comparator.comparingInt(CommonMenuList::getSort).reversed())
            .collect(Collectors.toList());

    menuVo.setList(format(projectMenus));
    LOGGER.info(projectMenus.toString());
    LOGGER.info(String.valueOf(projectMenus.size()));
    menuVo.setTotal(projectMenus.size());
    return menuVo;
  }

  @PostMapping("/listPage")
  public IPage<List<CommonMenuList>> menuListPage(HttpServletRequest request, @RequestBody Page page) {
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
              menuListVo.setMeta(meta);
              // 其他字段
              menuListVo.setComponent(item.getComponent());
              menuListVo.setPath(item.getPath());
              menuListVo.setName(item.getName());
              menuListVo.setRedirect(item.getRedirect());
              menuListVo.setChildren(format(item.getChildren()));
              menuListVo.setId(String.valueOf(item.getCatId()));
              return menuListVo;
            }
    ).collect(Collectors.toList());
  }

  public static List<CommonMenuList> getChildren(CommonMenuList root, List<CommonMenuList> allMenus) {
    return allMenus.stream()
            .filter(item -> Objects.equals(item.getParentCid(), root.getCatId()))
            .peek(item -> item.setChildren(Menus.getChildren(item, allMenus)))
            .sorted(Comparator.comparingInt(CommonMenuList::getSort).reversed())
            .collect(Collectors.toList());
  }


}

