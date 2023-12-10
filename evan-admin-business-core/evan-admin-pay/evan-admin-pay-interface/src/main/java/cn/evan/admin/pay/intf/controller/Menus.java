package cn.evan.admin.pay.intf.controller;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evan.admin.user.application.service.menu.MenuApplication;
import cn.evan.admin.user.sdk.feign.api.UserFeignMenuProvider;
import cn.evan.admin.user.sdk.feign.dto.MenuDTO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/menu")
public class Menus implements UserFeignMenuProvider {

  @Resource
  MenuApplication menusApplication;

  @Override
  @PostMapping("/list")
  public MenuDTO project(HttpServletRequest request) {
    return menusApplication.project(request);
  }

  @Override
  @PostMapping("/listPage")
  public IPage<CommonMenuList> menuListPage(HttpServletRequest request, @RequestBody Page page) {
    return menusApplication.menuListPage(request, page);
  }
}

