package cn.evanzuo.admin.business.menu.service;

import cn.evan.zuo.common.dto.CommonPageDTO;
import cn.evan.zuo.common.vo.CommonPageVo;
import cn.evan.zuo.common.vo.UpdateResult;
import cn.evanzuo.admin.business.menu.DTO.UpdateUrlDTO;
import cn.evanzuo.admin.business.menu.DTO.UrlListDTO;
import cn.evanzuo.admin.business.menu.VO.RoleListVo;
import cn.evanzuo.admin.business.menu.entity.AuthUrl;
import cn.evanzuo.admin.business.menu.entity.BusinessModuleEntity;
import cn.evanzuo.admin.business.menu.entity.RoleUrlItem;
import cn.evanzuo.admin.business.menu.service.imp.IUrlServiceImp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlService {
    @Resource
    IUrlServiceImp iUrlServiceImp;
    public CommonPageVo<AuthUrl> getUrlList(UrlListDTO commonPageDTO) {
        CommonPageVo<AuthUrl> a = new CommonPageVo<>();
        Page<RoleListVo> page = new Page<>();
        page.setSize(commonPageDTO.getPageSize());
        page.setCurrent(commonPageDTO.getPage());
        IPage<AuthUrl> urlList = iUrlServiceImp.getBaseMapper().getUrlList(page, commonPageDTO.getModuleId());
        a.setTotal((int) urlList.getTotal());
        a.setItems(urlList.getRecords());
        return a;
    }

    public List<BusinessModuleEntity> getModuleList() {
        return iUrlServiceImp.getBaseMapper().getModuleList();
    }

    public UpdateResult updateAuthUrl(UpdateUrlDTO updateUrlDTO) {
        iUrlServiceImp.getBaseMapper().updateAuthUrl(
                updateUrlDTO.getId(),
                updateUrlDTO.getModuleId(),
                updateUrlDTO.getPath()
        );
        return new UpdateResult();
    }
    public UpdateResult addAuthUrl(UpdateUrlDTO updateUrlDTO) {
        iUrlServiceImp.getBaseMapper().addAuthUrl(
                updateUrlDTO.getModuleId(),
                updateUrlDTO.getPath()
        );
        return new UpdateResult();
    }

    public UpdateResult deleteAuthUrl(UpdateUrlDTO updateUrlDTO) {
        iUrlServiceImp.getBaseMapper().deleteAuthUrl(
                updateUrlDTO.getId()
        );
        return new UpdateResult();
    }

    public UpdateResult updateRoleUrl() {
        // 角色管理的url列表
        // 传递的角色管理url列表

        // 删除所有原来关系
        // 插入新关系
        List<RoleUrlItem> roleUrlItems = new ArrayList<>();
        RoleUrlItem a = new RoleUrlItem();
        a.setRoleId(9);
        a.setUrlId(99);
        RoleUrlItem a2 = new RoleUrlItem();
        a2.setRoleId(999);
        a2.setUrlId(99999);
        roleUrlItems.add(a);
        roleUrlItems.add(a2);
        iUrlServiceImp.getBaseMapper().updateRoleUrl(roleUrlItems);
        return new UpdateResult();
    }
}
