package cn.evan.admin.user.domain.serviceOld.imp;

import cn.evan.admin.user.domain.mapper.DeptMapper;
import cn.evan.admin.user.domain.serviceOld.IDeptService;
import cn.evan.zuo.common.entity.CommonMenuList;
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
public class IDeptServiceImp extends ServiceImpl<DeptMapper, CommonMenuList> implements IDeptService {
}
