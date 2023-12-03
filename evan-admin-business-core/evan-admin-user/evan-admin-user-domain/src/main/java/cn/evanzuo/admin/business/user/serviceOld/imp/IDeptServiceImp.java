package cn.evanzuo.admin.business.user.serviceOld.imp;

import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.user.mapper.DeptMapper;
import cn.evanzuo.admin.business.user.serviceOld.IDeptService;
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