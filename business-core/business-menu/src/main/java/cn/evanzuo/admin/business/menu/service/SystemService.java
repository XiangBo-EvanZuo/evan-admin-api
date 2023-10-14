package cn.evanzuo.admin.business.menu.service;

import cn.evan.zuo.common.dto.CommonPageDTO;
import cn.evan.zuo.common.vo.CommonPageVo;
import cn.evanzuo.admin.business.menu.DTO.AccountExistDTO;
import cn.evanzuo.admin.business.menu.VO.AccountExistVo;
import cn.evanzuo.admin.business.menu.VO.RoleListVo;
import cn.evanzuo.admin.business.menu.service.imp.IDeptServiceImp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class SystemService {
    @Resource
    IDeptServiceImp iDeptServiceImp;
    public AccountExistVo accountExist(String username) {
        Integer existNumber = iDeptServiceImp.getBaseMapper().exist(username);
        AccountExistVo accountExist = new AccountExistVo();
        accountExist.setExist(existNumber > 0);
        accountExist.setMessage("该用户已存在！");
    	return accountExist;
    }

    public CommonPageVo<RoleListVo> getRoleListByPage(CommonPageDTO commonPageDTO) {
        Page<RoleListVo> page = new Page<>();
        page.setSize(commonPageDTO.getPageSize());
        page.setCurrent(commonPageDTO.getPage());
        IPage<RoleListVo> roleListByPage = iDeptServiceImp.getBaseMapper().getRoleListByPage(page);
        CommonPageVo<RoleListVo> accountExist = new CommonPageVo<>();
        accountExist.setTotal((int) roleListByPage.getTotal());
        accountExist.setItems(roleListByPage.getRecords());
        return accountExist;
    }
}
