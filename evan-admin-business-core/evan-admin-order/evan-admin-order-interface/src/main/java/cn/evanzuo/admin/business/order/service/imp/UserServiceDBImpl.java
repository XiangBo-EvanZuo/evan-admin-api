package cn.evanzuo.admin.business.order.service.imp;

import cn.evanzuo.admin.business.order.domain.User;
import cn.evanzuo.admin.business.order.mapper.UserMapper;
import cn.evanzuo.admin.business.order.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linlaoshi
 * @since 2022-12-31
 */
@Service
public class UserServiceDBImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
