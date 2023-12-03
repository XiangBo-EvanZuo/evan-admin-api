package cn.evan.admin.pay.intf.service.imp;

import cn.evan.admin.pay.intf.domain.User;
import cn.evan.admin.pay.intf.mapper.UserMapper;
import cn.evan.admin.pay.intf.service.IUserService;
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
