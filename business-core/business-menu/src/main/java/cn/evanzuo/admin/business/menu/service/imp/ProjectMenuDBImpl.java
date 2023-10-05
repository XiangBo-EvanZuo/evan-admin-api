package cn.evanzuo.admin.business.menu.service.imp;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.menu.mapper.MenuMapper;
import cn.evanzuo.admin.business.menu.service.IMenuSerive;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author EvanZuo
 * @since 2022-12-31
 */
@Service
public class ProjectMenuDBImpl extends ServiceImpl<MenuMapper, CommonMenuList> implements IMenuSerive {
}
