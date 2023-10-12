package cn.evanzuo.admin.business.menu.service;

import cn.evanzuo.admin.business.menu.VO.AccountExistVo;
import org.springframework.stereotype.Service;

@Service
public class SystemService {
    public AccountExistVo accountExist() {
        AccountExistVo accountExist = new AccountExistVo();
        accountExist.setExist(true);
    	return accountExist;
    }
}
