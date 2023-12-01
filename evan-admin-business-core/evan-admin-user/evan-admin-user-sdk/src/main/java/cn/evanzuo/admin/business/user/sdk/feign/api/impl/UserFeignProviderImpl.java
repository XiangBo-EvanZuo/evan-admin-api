package cn.evanzuo.admin.business.user.sdk.feign.api.impl;
import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.user.sdk.feign.api.UserFeignMenuProvider;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UserFeignProviderImpl implements UserFeignMenuProvider {

    @Override
    public MenuVo project(HttpServletRequest request) {
        return null;
    }

    @Override
    public IPage<List<CommonMenuList>> menuListPage(HttpServletRequest request, Page page) {
        return null;
    }
}
