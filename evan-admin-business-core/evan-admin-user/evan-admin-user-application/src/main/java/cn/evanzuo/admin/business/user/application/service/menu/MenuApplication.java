package cn.evanzuo.admin.business.user.application.service.menu;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuListVo;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuVo;
import cn.evanzuo.admin.business.user.controller.MenusService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class MenuApplication {
    @Resource
    MenusService menusService;
    public MenuVo project(HttpServletRequest request) {
        return menusService.project(request);
    }

    public IPage<List<CommonMenuList>> menuListPage(HttpServletRequest request, Page page) {
        return menusService.menuListPage(request, page);
    }
}


