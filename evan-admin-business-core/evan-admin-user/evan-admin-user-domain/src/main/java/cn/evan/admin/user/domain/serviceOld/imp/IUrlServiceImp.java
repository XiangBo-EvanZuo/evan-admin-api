package cn.evan.admin.user.domain.serviceOld.imp;

import cn.evan.admin.user.domain.entity.AuthUrl;
import cn.evan.admin.user.domain.mapper.UrlMapper;
import cn.evan.admin.user.domain.serviceOld.IUrlSerive;
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
