package cn.evanzuo.admin.business.menu.service;

import cn.evan.zuo.common.dto.CommonPageDTO;
import cn.evan.zuo.common.vo.CommonPageVo;
import cn.evan.zuo.common.vo.UpdateResult;
import cn.evanzuo.admin.business.menu.DTO.UpdateUrlDTO;
import cn.evanzuo.admin.business.menu.DTO.UrlListDTO;
import cn.evanzuo.admin.business.menu.VO.RoleListVo;
import cn.evanzuo.admin.business.menu.entity.AuthUrl;
import cn.evanzuo.admin.business.menu.entity.BusinessModuleEntity;
import cn.evanzuo.admin.business.menu.service.imp.IUrlServiceImp;
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
}
