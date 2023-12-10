package cn.evan.admin.pay.application.service.menu;

import cn.evan.admin.pay.application.convertor.menu.MenuVO2DTO;
import cn.evan.admin.user.domain.aggregate.menu.entity.UserMenuEntity;
import cn.evan.admin.user.sdk.feign.dto.MenuDTO;
import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evan.admin.user.domain.service.menu.MenusDomainService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class MenuApplication {
    @Resource
    MenusDomainService menusDomainService;

    @Resource
    MenuVO2DTO menuVO2DTO;
    public MenuDTO project(HttpServletRequest request) {
        List<UserMenuEntity> menuEntities = menusDomainService.project(request);
        MenuDTO menuDTO = new MenuDTO();
        log.info("menuEntities: {}", menuEntities);
        menuDTO.setTotal(menuEntities.size());
        menuDTO.setList(menuVO2DTO.convertorVO2UO(menuEntities));
        return menuDTO;
    }

    public IPage<CommonMenuList> menuListPage(HttpServletRequest request, Page page) {
        IPage<CommonMenuList> commonMenuLists = menusDomainService.menuListPage(request, page);
        List<UserMenuEntity> menuListIPage = menuVO2DTO.convertor(commonMenuLists.getRecords());
        log.info("menuListIPage:{}", menuListIPage);
        return commonMenuLists;
    }
}


