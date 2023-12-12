package cn.evan.admin.user.sdk.feign.api;

import cn.evan.admin.user.sdk.feign.dto.CurrentUserDTO;
import cn.evan.admin.user.sdk.feign.dto.MenuDTO;
import cn.evan.zuo.common.entity.CommonMenuList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;

public interface UserFeignMenuProvider {
    default MenuDTO menuListFeign(String userHeaderString) {
        return null;
    };
    default MenuDTO project(HttpServletRequest request) {
        return null;
    };
    IPage<CommonMenuList> menuListPage(HttpServletRequest request, Page page);

    default CurrentUserDTO getCurrentUser(String userStr) throws JsonProcessingException {
        return null;
    };
}
