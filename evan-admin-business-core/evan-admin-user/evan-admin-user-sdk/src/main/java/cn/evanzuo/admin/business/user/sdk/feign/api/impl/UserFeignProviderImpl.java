package cn.evanzuo.admin.business.user.sdk.feign.api.impl;
import cn.evan.zuo.common.entity.CommonMenuList;
import cn.evanzuo.admin.business.user.sdk.feign.api.UserFeignMenuProvider;
import cn.evanzuo.admin.business.user.sdk.feign.dto.MenuVo;
import cn.evanzuo.admin.common.feign.client.clients.EvanFeignUserInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
public class UserFeignProviderImpl {

    @Autowired
    EvanFeignUserInfo evanFeignUserInfo;
    public String project(String user) {
        return evanFeignUserInfo.getUserMenuList(user);
    }

    public IPage<List<CommonMenuList>> menuListPage(HttpServletRequest request, Page page) {
        return null;
    }
}
