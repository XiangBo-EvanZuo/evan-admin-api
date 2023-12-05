package cn.evan.admin.user.domain.serviceOld;

import cn.evan.admin.user.domain.entity.AuthUrl;
import cn.evan.admin.user.domain.entity.BusinessModuleEntity;
import cn.evan.admin.user.sdk.feign.dto.RoleListVo;
import cn.evan.zuo.common.vo.CommonPageVo;
import cn.evan.zuo.common.vo.UpdateResult;
import cn.evan.admin.user.domain.DTO.UpdateRoleUrlDTO;
import cn.evan.admin.user.domain.DTO.UpdateUrlDTO;
import cn.evan.admin.user.domain.DTO.UrlListDTO;
import cn.evan.admin.user.domain.serviceOld.imp.IUrlServiceImp;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public UpdateResult updateRoleUrl(UpdateRoleUrlDTO updateRoleUrlDTO) {
        // 角色管理的url列表
        // 传递的角色管理url列表

        // 删除所有原来关系
        // 插入新关系
        List<Integer> roleUrlItems = updateRoleUrlDTO.getList();
        iUrlServiceImp.getBaseMapper().updateRoleUrl(updateRoleUrlDTO.getRoleId(), roleUrlItems);
        return new UpdateResult();
    }

    public UpdateResult updateRoleMenu(UpdateRoleUrlDTO updateRoleUrlDTO) {
        // 角色管理的url列表
        // 传递的角色管理url列表

        // 删除所有原来关系
        // 插入新关系
        List<Integer> roleMenuItems = updateRoleUrlDTO.getList();
        iUrlServiceImp.getBaseMapper().updateRoleMenu(updateRoleUrlDTO.getRoleId(), roleMenuItems);
        return new UpdateResult();
    }
}
