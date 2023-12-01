package cn.evanzuo.admin.business.user.sdk.feign.api;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuListVo;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserFeignMenuProvider {
    MenuVo project(HttpServletRequest request);
    IPage<List<CommonMenuList>> menuListPage(HttpServletRequest request, Page page);
}