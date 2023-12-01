package cn.evanzuo.admin.business.user.intf.controller;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.user.application.service.menu.MenuApplication;
import cn.evanzuo.admin.business.user.sdk.feign.api.UserFeignMenuProvider;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController()
@RequestMapping("/menu")
public class Menus implements UserFeignMenuProvider {

  @Resource
  MenuApplication menusApplication;

  @Override
  @PostMapping("/list")
  public MenuVo project(HttpServletRequest request) {
    return menusApplication.project(request);
  }

  @Override
  @PostMapping("/listPage")
  public IPage<List<CommonMenuList>> menuListPage(HttpServletRequest request, @RequestBody Page page) {
    return menusApplication.menuListPage(request, page);
  }
}

