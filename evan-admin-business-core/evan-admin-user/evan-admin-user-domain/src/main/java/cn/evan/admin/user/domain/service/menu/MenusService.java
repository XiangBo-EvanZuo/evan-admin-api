package cn.evan.admin.user.domain.service.menu;

import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import cn.evan.admin.user.domain.aggregate.menu.repository.UserMenuRepository;
import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evan.admin.user.sdk.feign.dto.MenuListVo;
import cn.evan.admin.user.sdk.feign.dto.MenuVo;
import cn.evan.admin.user.sdk.feign.dto.Meta;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MenusService {
  @Resource
  UserMenuRepository userMenuRepository;

  public List<UserMenuEntity> project(HttpServletRequest request) {
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
    List<UserMenuEntity> allMenus = userMenuRepository.getMenuListByRole(authoritiesStr);
    return allMenus;
  }

  public IPage<CommonMenuList> menuListPage(HttpServletRequest request, Page page) {
      String userStr = request.getHeader("user");
      JSONObject userJsonObject = new JSONObject(userStr);
      System.out.println((userJsonObject));
      QueryWrapper queryWrapper = new QueryWrapper();
      IPage<CommonMenuList> allMenus = userMenuRepository.basePage(page, queryWrapper);
      return allMenus;
  }
}

