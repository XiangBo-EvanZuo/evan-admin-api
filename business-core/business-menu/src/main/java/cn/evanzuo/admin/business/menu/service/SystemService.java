package cn.evanzuo.admin.business.menu.service;

import cn.evanzuo.admin.business.menu.DTO.AccountExistDTO;
import cn.evanzuo.admin.business.menu.VO.AccountExistVo;
import cn.evanzuo.admin.business.menu.service.imp.IDeptServiceImp;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class SystemService {
    @Resource
    IDeptServiceImp iDeptServiceImp;
    public AccountExistVo accountExist(String username) {
        System.out.println("123");
        System.out.println(username);

        Integer existNumber = iDeptServiceImp.getBaseMapper().exist(username);
        AccountExistVo accountExist = new AccountExistVo();
        accountExist.setExist(existNumber > 0);
        accountExist.setMessage("not");
    	return accountExist;
    }
}
