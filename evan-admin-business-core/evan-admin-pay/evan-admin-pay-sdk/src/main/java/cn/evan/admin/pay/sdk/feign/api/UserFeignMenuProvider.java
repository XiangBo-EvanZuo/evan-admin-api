package cn.evan.admin.pay.sdk.feign.api;

import cn.evan.admin.pay.sdk.feign.dto.MenuVo;
import cn.evan.zuo.common.entity.CommonMenuList;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;

public interface UserFeignMenuProvider {
    default MenuVo menuListFeign(String userHeaderString) {
        return null;
    };
    default MenuVo project(HttpServletRequest request) {
        return null;
    };
    IPage<CommonMenuList> menuListPage(HttpServletRequest request, Page page);
}
