package cn.evan.admin.user.infrastructure.repository.menu;

import cn.evan.admin.user.infrastructure.repository.menu.mapper.UserMenuMapper;
import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evan.admin.user.domain.aggregate.menu.repository.UserMenuRepository;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Slf4j
public class UserMenuRepositoryImpl
        extends ServiceImpl<UserMenuMapper, CommonMenuList>
        implements UserMenuRepository {

    @Override
    public List<CommonMenuList> getMenuListByRole(String roleNames) {
        return this.getBaseMapper().getMenuListByRole(roleNames);
    }

    @Override
    public IPage<List<CommonMenuList>> basePage(Page page, QueryWrapper queryWrapper) {
        log.info("page: {}, queryWrapper: {}", page, queryWrapper);
        return this.page(page, queryWrapper);
    }
}
