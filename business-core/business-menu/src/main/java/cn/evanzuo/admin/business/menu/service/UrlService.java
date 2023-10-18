package cn.evanzuo.admin.business.menu.service;

import cn.evan.zuo.common.dto.CommonPageDTO;
import cn.evan.zuo.common.vo.CommonPageVo;
import cn.evanzuo.admin.business.menu.VO.AccountExistVo;
import cn.evanzuo.admin.business.menu.VO.RoleListFinalVo;
import cn.evanzuo.admin.business.menu.VO.RoleListVo;
import cn.evanzuo.admin.business.menu.entity.AuthUrl;
import cn.evanzuo.admin.business.menu.service.imp.IDeptServiceImp;
import cn.evanzuo.admin.business.menu.service.imp.IUrlServiceImp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UrlService {
    @Resource
    IDeptServiceImp iDeptServiceImp;
    @Resource
    IUrlServiceImp iUrlServiceImp;
    public CommonPageVo<AuthUrl> getUrlList(CommonPageDTO commonPageDTO) {
        CommonPageVo<AuthUrl> a = new CommonPageVo<>();
        Page<RoleListVo> page = new Page<>();
        page.setSize(commonPageDTO.getPageSize());
        page.setCurrent(commonPageDTO.getPage());
        IPage<AuthUrl> urlList = iUrlServiceImp.getBaseMapper().getUrlList(page);
        a.setTotal((int) urlList.getTotal());
        a.setItems(urlList.getRecords());
        return a;
    }
    public AccountExistVo accountExist(String username) {
        Integer existNumber = iDeptServiceImp.getBaseMapper().exist(username);
        AccountExistVo accountExist = new AccountExistVo();
        accountExist.setExist(existNumber > 0);
        accountExist.setMessage("该用户已存在！");
    	return accountExist;
    }

    public CommonPageVo<RoleListFinalVo> getRoleListByPage(CommonPageDTO commonPageDTO) {
        Page<RoleListVo> page = new Page<>();
        page.setSize(commonPageDTO.getPageSize());
        page.setCurrent(commonPageDTO.getPage());
        IPage<RoleListVo> roleListByPage = iDeptServiceImp.getBaseMapper().getRoleListByPage(page);
        CommonPageVo<RoleListFinalVo> accountExist = new CommonPageVo<>();
        List<RoleListFinalVo> roleListFinalVos = roleListByPage
                .getRecords()
                        .stream().map(item -> {
                    RoleListFinalVo roleListFinalVo = new RoleListFinalVo();
                    roleListFinalVo.setId(item.getId());
                    roleListFinalVo.setRoleName(item.getRoleName());
                    roleListFinalVo.setMenu(
                            Arrays.stream(item.getMenu().split(","))
                                    .filter(each -> !StringUtils.isEmpty(each))
                                    .collect(Collectors.toList())
                    );
                    roleListFinalVo.setUrlList(
                            Arrays.stream(item.getUrlList().split(","))
                                .filter(each -> !StringUtils.isEmpty(each))
                                .collect(Collectors.toList()));
                    roleListFinalVo.setRemark(item.getRemark());
                    roleListFinalVo.setStatus(item.getStatus());
                    return roleListFinalVo;
                }).collect(Collectors.toList());
        accountExist.setTotal((int) roleListByPage.getTotal());
        accountExist.setItems(roleListFinalVos);
        return accountExist;
    }
}
