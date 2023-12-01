package cn.evanzuo.admin.business.user.service.imp;

import cn.evanzuo.admin.business.user.entity.AuthUrl;
import cn.evanzuo.admin.business.user.mapper.UrlMapper;
import cn.evanzuo.admin.business.user.service.IUrlSerive;
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
public class IUrlServiceImp extends ServiceImpl<UrlMapper, AuthUrl> implements IUrlSerive {
}
