package cn.evanzuo.admin.business.user.aggregate.menu.repository;

import cn.evan.zuo.common.entity.CommonMenuList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public interface UserMenuRepository {
    List<CommonMenuList> getMenuListByRole(String roleNames);
    IPage<List<CommonMenuList>> basePage(Page page, QueryWrapper queryWrapper);

}
